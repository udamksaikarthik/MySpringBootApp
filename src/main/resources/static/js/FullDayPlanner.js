const add_event_div_id = document.getElementById('add_event_div_id')

function addEvent(){
	add_event_div_id.classList.add('slide');
}

function closeAddEvent(){
	add_event_div_id.classList.remove('slide');
	setTimeout(function(){
		window.location.href = '/apps/24_hour_planner';
	},1000)
}