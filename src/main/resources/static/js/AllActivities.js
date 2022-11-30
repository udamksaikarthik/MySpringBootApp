function closeAddEvent(){
		window.location.href = '/apps/24_hour_planner';
}

const all_activities_main_container = document.getElementById('all_activities_main_container')

if(all_activities_main_container.offsetHeight < window.innerHeight){
	all_activities_main_container.style.height = '100vh';
}

