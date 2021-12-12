function createBooks() {
    //lấy dữ liệu
    let bookName = $('#nameBook').val();
    let authorBook = $('#authorBook').val();
    let quantityBook = $('#quantityBook').val();
    let newBook = {
        name: bookName,
        author: authorBook,
        quantity: quantityBook
    };
    //gọi ajax
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        type: "POST",
        data: JSON.stringify(newBook),
        url: "/books",
        success: successCreate
    });
    event.preventDefault();
}

function successCreate() {
    $.ajax({
        type: "GET",
        url: "/books/",
        success: function (data) {
            let content2 = '<input type="text" id="nameBook" placeholder="NameOfBook">\n' +
                ' <input type="text" id="authorBook" placeholder="AuthorOfBook">\n' +
                ' <input type="text" id="quantityBook" placeholder="QuantityOfBook">\n' +
                '<input type="submit" onClick="createBooks()" value="Create">\n';

            let content = '<tr>\n' +
                ' <th>Name:</th>\n' +
                ' <th>Author:</th>\n' +
                '<th>Quantity:</th>\n' +
                ' <th>Borrow</th>\n' +
                ' </tr>\n';
            for (let i = 0; i < data.length; i++) {
                content += getBook(data[i]);
            }
            document.getElementById('books').innerHTML = content;
            document.getElementById('createBook').innerHTML = content2;
        }
    });

    function getBook(book) {
        return `<tr><td>${book.name}</td>` +
            `<td>${book.author}</td>` +
            `<td>${book.quantity}</td>` +
            `<td><button type="submit" className="borrowBooks" onclick="showBorrowBooks(this)" value="${book.id}">Borrow</button></td></tr>`
    }
}

function showBorrowBooks(id) {
    let idBook = id.getAttribute("value");
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "GET",
        url: "/cards/" + idBook,
        success: function (data) {
            let content = '<form>\n' +
                '<table>\n' +
                '<caption>BorrowBook</caption>\n' +
                '<tr>\n' +
                '<td><input type="hidden" id="idCard" value="' + data.id + '"></td>\n' +
                '<td><input type="hidden" id="idBook" value="' + data.book.id + '"></td>\n' +
                '<td><input disabled type="text" id="nameBook" value="' + data.book.name + '"></td>\n' +
                '</tr>\n' +
                '<tr>\n' +
                '<td><input disabled type="text" id="authorBook" value="' + data.book.author + '"></td>\n' +
                '<td><input type="text" id="idStudent"></td>\n' +
                '<td><input type="hidden" id="codeCard" value="' + data.code + '"></td>\n' +
                '</tr>\n' +
                '</table>\n' +
                '<button type="submit" value="borrow" onclick="borrowBook()">Borrow</button>\n' +
                '</form>';
            document.getElementById('createBook').innerHTML = content;

        }
    });
    event.preventDefault();
}

function borrowBook() {
    let idBook = $('#idBook').val();
    let nameBook = $('#nameBook').val();
    let authorBook = $('#authorBook').val();
    let newBook = {
        id: idBook,
        name: nameBook,
        author: authorBook
    }
    let idStudent = $('#idStudent').val();
    let newStudent = {
        id: idStudent
    };
    let newCard = {
        book: newBook,
        student: newStudent
    };
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(newCard),
        url: "/cards",
        error: errorCreate,
        success: successCreate
    });
    event.preventDefault();
}
function errorCreate() {
    $.ajax({
        type: "GET",
        url: "/error"
    });
}

    function createStudent() {
    let nameStudent = $('#nameStudent').val();
    let identityStudent = $('#identityStudent').val();
    let newStudent = {
    name: nameStudent,
    identity: identityStudent
};
    $.ajax({
    headers: {
    'Accept': 'application/json',
    'Content-Type': 'application/json'
},
    type: "POST",
    data: JSON.stringify(newStudent),
    url: "/students",
    success: successCreateStudent
});
    event.preventDefault()
}

    function successCreateStudent() {
    $.ajax({
        type: "GET",
        url: "/students/",
        success: function (data) {
            let content = '    <tr>\n' +
                '        <th>Name</th>\n' +
                '        <th>Identity</th>\n' +
                '        <th>Delete</th>\n' +
                '        <th>Edit</th>\n' +
                '    </tr>';
            for (let i = 0; i < data.length; i++) {
                content += getStudent(data[i]);
            }
            document.getElementById('books').innerHTML = content;
        }
    });
}

    function getStudent(student) {
    return `<tr><td>${student.name}</td><td>${student.identity}</td>` +
    `<td><button type="submit" class="delete" value="${student.id}" onclick="deleteStudent(this)">Delete</button></td>` +
    `<td><button type="submit" class="edit" value="${student.id}" onclick="editStudent(this)">Edit</button></td>` +
    `</tr>`;
}

    function deleteStudent(id) {
    let idStudent = id.getAttribute("value");
    $.ajax({
    type: "DELETE",
    url: "/students/" +idStudent,
    success: successCreateStudent
});
    event.preventDefault();
}

    function showEditStudent(id) {
    let idStudent = id.getAttribute("value");
}
