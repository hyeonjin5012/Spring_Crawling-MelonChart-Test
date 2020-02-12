/**
 * 
 */

function page(num){

	$.ajax({
		type : 'GET',
		url : '/api/melonchart/'+num,
		dataType : 'json'
	}).done(function(result) {
		console.log(result)
		$('.card--items').remove();
		result.forEach(function(chart) {
			var card_item=`<div class="col-md-6 col-lg-4 my-3 card--items">`;
				card_item+=`<div class="card">`;
				card_item+=`<img src="${chart.photo}" class="card-img-top mx-auto" alt="" style="width: 200px;" />`;
				card_item+=`<div class="card-header d-flex">${chart.ranking}위`;
				card_item+=`<button class="ml-auto"onclick="music_play('${chart.musicLink}')">▶</button>`;
				card_item+=`<button onclick="music_stop('${chart.musicLink}')">ΙΙ</button>`;
				card_item+=`<button onclick="music_replay('${chart.musicLink}')">■</button>`;
				card_item+=`</div>`;
				card_item+=`<div class="card-body">`;
				card_item+=`<h5 class="card-title text-capitalize">${chart.title}</h5>`;
				card_item+=`<p class="card-text">${chart.singer}</p>`;
				card_item+=`</div>`;
				card_item+=`</div>`;
					card_item+=`</div>`;
			$('#chart--title').append(card_item);
		});
	
	}).fail(function(result) {

		alert('서버오류');
	});
}
function music_play(music_link){
	$('.my--iframe').remove();
	var iframe_item=`<iframe class='my--iframe'id="`
		+music_link
		+`"src='https://www.youtube.com/embed/`
		+music_link
		+`?autoplay=1&mute=0' allow='autoplay's></iframe>`;
	$('#test').append(iframe_item);
}
function music_stop(music_link){
	$('#'+music_link).stopVideo();
	
}
function music_replay(music_link){


}