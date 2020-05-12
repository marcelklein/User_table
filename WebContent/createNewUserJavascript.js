//Form
function adjust_textarea(h) {
	h.style.height = '20px';
	h.style.height = h.scrollHeight + 'px';
}

//Bin für eine besser Lösung offen! Baue dies in ein Promise zusammen mit dem Ajax call um, baue Ajax in JSON um
//https://stackoverflow.com/questions/5000415/call-a-function-after-previous-function-is-complete
let obj = {};
$('#createArr').click(() => {
	let tempSurname, tempFirstname, tempAddress, tempCity, tempZipCode;
	tempSurname = document.getElementById('userInfoSurname').value;
	tempFirstname = document.getElementById('userInfoFirstname').value;
	tempAddress = document.getElementById('userInfoAddress').value;
	tempCity = document.getElementById('userInfoCity').value;
	tempZipCode = document.getElementById('userInfoZipcode').value;

	obj = { 
			surname: tempSurname,
			firstname: tempFirstname,
			address: tempAddress,
			city: tempCity,
			zipCode: tempZipCode
		};
	
	const ajaxPost = $.ajax({
		url: 'http://localhost:8080/web_project/CreateUser/CreateUser',
		data: JSON.stringify({
			"newUser": obj
		}),
//			image: rawImg
		type: 'POST',
		datatype: 'JSON',
		headers: {
			operation: 'save',
			ContentType: 'application/json; charset=UTF-8'
		},
		error: function() {
			swal({
				title: 'Good job!',
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
				text: 'New user is created!',
				icon: 'success',
				button: 'OK'
			}).then((realoadPage) => {
				if (realoadPage) {
					location.reload();
				}
			});

		}
	});
	console.log(obj);
	console.log(ajaxPost);
});
//Bin für eine besser Lösung offen!

//Cropper
$('.imgPlaceholder').attr('src', 'https://user.gadjian.com/static/images/personnel_boy.png');
let uploadCrop, tempFilename, rawImg, imageId;
function readFile(input) {
	//File Reader function
	if (input.files && input.files[0]) {
		const reader = new FileReader();
		reader.onload = function(e) {
			//Display selected image inside a dialog
			$('.upload-demo').addClass('ready');
			$('#cropImagePop').modal('show');
			rawImg = e.target.result;
			//console.log(rawImg); //rawImg = Base64
		};
		reader.readAsDataURL(input.files[0]);
	}
}

//Cropper UI display
uploadCrop = $('#upload-demo').croppie({
	//cropper UI
	viewport: {
		//cropper parameter
		width: 150,
		height: 200,
		type: 'square',
		showZoomer: true,
		enableOrientation: true
	},
	boundary: {
		width: 150,
		height: 200
	},
	enableExif: true
});

//Cropper bootstrap modal dialog
$('#cropImagePop').on('shown.bs.modal', function() {
	//cropper dialog
	uploadCrop
		.croppie('bind', {
			url: rawImg
		})
		.then(function() {});
});

$('.item-img').on('change', function() {
	imageId = $(this).data('id');
	tempFilename = $(this).val();
	$('#cancelCropBtn').data('id', imageId);
	readFile(this);
});

$('#cropImageBtn').on('click', function() {
	//result by click event
	uploadCrop
		.croppie('result', {
			//result parameter
			type: 'base64',
			format: 'jpeg',
			size: { width: 150, height: 200 }
		})
		.then(function(resp) {
			$('#item-img-output').attr('src', resp); //display result on selected image frame
			$('#cropImagePop').modal('hide');
		});
});
