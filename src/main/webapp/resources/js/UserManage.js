function checkLogin() {
    fetch('http://localhost:8081/session')
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json(); // JSON 응답일 경우 response.json() 사용
    }).then(data => {
        if (!data.success) {
            window.location.href = '/resources/login.html';
        } else {
            return null;
        }

    })
    .catch(error => console.log(error));
}

function getSession() {
    fetch('http://localhost:8081/session')
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json(); // JSON 응답일 경우 response.json() 사용
    })
    .then(data => {
        if (data != null) {
            const sessionContainer = document.getElementById('session-container');
            sessionContainer.innerHTML = '';
            const dto = data.dto;
                const itemDiv = document.createElement('div');
                itemDiv.className = 'user-item';

                itemDiv.innerHTML = `
                    <p><strong>로그인한 사용자</strong></p>
                    <br>
                    <p><strong>Email:</strong> ${dto.userEmail}</p>
                    <p><strong>Name:</strong> ${dto.userName}</p>
                    <p><strong>Gender:</strong> ${dto.userGender}</p>
                    <p><strong>Region:</strong> ${dto.userRegion}</p>
                    <p><strong>Secret:</strong> ${dto.userSecret}</p>
                    <br>
                `;
                console.log(data);
                sessionContainer.appendChild(itemDiv);
        } else {
            console.error('Expected an array but received:', data);
        }
    })
    .catch(error => {
        console.error('Error:', error);
        const sessionContainer = document.getElementById('session-container');
        sessionContainer.textContent = 'Error loading data.';
    });
}

function loadData() {
    fetch('http://localhost:8081/fetch')
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json(); // JSON 응답일 경우 response.json() 사용
    })
    .then(data => {
        if (Array.isArray(data)) {
            const dataContainer = document.getElementById('data-container');
            dataContainer.innerHTML = '전체 사용자';

            data.forEach(item => {
                const itemDiv = document.createElement('div');
                itemDiv.className = 'user-item';

                itemDiv.innerHTML = `
                    <p><strong>Email:</strong> ${item.userEmail}</p>
                    <p><strong>Name:</strong> ${item.userName}</p>
                    <p><strong>Gender:</strong> ${item.userGender}</p>
                    <p><strong>Region:</strong> ${item.userRegion}</p>
                    <p><strong>Secret:</strong> ${item.userSecret}</p>
                    <br>
                `;

                dataContainer.appendChild(itemDiv);
            });
        } else {
            console.error('Expected an array but received:', data);
        }
    })
    .catch(error => {
        console.error('Error:', error);
        const dataContainer = document.getElementById('data-container');
        dataContainer.textContent = 'Error loading data.';
    });
}


//로그인 된 사용자 재 로그인 방지

    function login() {
        var userEmail = document.getElementById('userEmail').value;
        var userSecret = document.getElementById('password').value;
        console.log(userEmail);
           fetch('http://localhost:8081/login', {
               method: 'POST',
               headers: {
                   'Content-Type': 'application/json'
               },
               body: JSON.stringify({
                   userEmail: userEmail,
                   userSecret: userSecret
               })
           })
           .then(response => {
               if (!response.ok) {
                   throw new Error('Network response was not ok');
               }
               return response.json();
           })
           .then(data => {
               console.log(data);
               console.log(data.success);
               console.log(data.dto);
               if (data.success) {
                   window.location.href = 'index.html';
               }

           })
           .catch(error => console.error('Error:', error));
               // 폼 제출을 방지하고, JavaScript에서 처리하도록 설정
               return false;

    }

//        fetch('http://localhost:8081/session')
//        .then(response => {
//            if (!response.ok) {
//                throw new Error('Network response was not ok');
//            }
//            if (response == null)
//            return response.json(); // JSON 응답일 경우 response.json() 사용
//        })
//        .then(data => {
//            if (data != null && data.userEmail != null) {
//                    window.location.href = 'index.html';
//            }
//        }).catch(error => console.error('Error:', error));

