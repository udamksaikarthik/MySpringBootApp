
const body_id = document.getElementById('body_id')

const body_height = body_id.offsetHeight;

if(body_height < window.innerHeight){
	console.log('body_height: ',body_height)
	console.log('window_height: ',window.innerHeight)
	body_id.style.height = '100vh';
}else{
	console.log('body_height: ',body_height)
	console.log('window_height: ',window.innerHeight)
}