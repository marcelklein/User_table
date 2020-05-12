// neues Json
$.ajax({
		url: 'http://localhost:8080/web_project/RequestSelectAllUser/RequestSelectAllUser',
		type: 'POST',
		datatype: 'JSON',
		headers: {
			operation: 'save',
			ContentType: 'application/json; charset=UTF-8'
		},
		error: function(xhr, status) {
			alert(status);
		},
		success: function(obj){
			displayJson(obj);
		}
	});

function displayJson(obj){
	
	let selectTable = document.getElementById("htmlTable");

	for (var key in obj) {
	    var objLoop = obj[key];
	    let newRow = selectTable.insertRow(-1);
// Style MUSS so festgelegt werden, Fix is doch irgendwie Zukunfts Marcel!
	    
// let newRowStyle = document.getElementsByTagName("td").setAttribute("class",
// "rowStyle");

	    console.log(objLoop);

	    for (var prop in objLoop) {
	        let newCell = newRow.insertCell(0);
	        let newContent = document.createTextNode(objLoop[prop]);
	        newCell.appendChild(newContent);
		    newCell.setAttribute("class", "cellStyle")

	        console.log(prop + " = " + objLoop[prop]);
	    }
	}	
}

// für modify https://swisnl.github.io/jQuery-contextMenu/ kontext
// menu
// http://ignitersworld.com/lab/contextMenu.html

//reload must be stoped
document.getElementById('getUserIdBtn').addEventListener('click', () => {
	let input = document.getElementById('delteInput').value;

	$.ajax({
		url: 'http://localhost:8080/web_project/DeleteUser/DeleteUser',
		type: 'POST',
		datatype: 'JSON',
		data: JSON.stringify({
			newUser: input
		}),
		headers: {
			operation: 'save',
			ContentType: 'application/json; charset=UTF-8'
		},
		error: function() {
			swal({
				title: ':(',
				text: 'The process failed!',
				icon: 'error',
				button: 'OK'
			}).then((realoadPage) => {
				if (realoadPage) {
					location.reload();
				}
			});

		},
		success: function() {
			swal({
				title: 'Good job!',
				text: 'The user with the ID:'+input+' has been deleted',
				icon: 'success',
				button: 'OK'
			}).then((realoadPage) => {
				if (realoadPage) {
					location.reload();
				}
			});

		}
	});
});