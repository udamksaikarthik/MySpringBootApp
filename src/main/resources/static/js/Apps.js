const apps_container_id = document.getElementById('apps_container_id')

if(apps_container_id.offsetHeight < window.innerHeight){
	apps_container_id.style.height = '100vh';
}