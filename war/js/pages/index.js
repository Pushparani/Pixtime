/**
 * methods page
 */

var i = 10;
(function(window) {

	'use strict';

	var MD = window.MD;
	var $ = window.jQuery;

	var heroContainer;
	var heroMasonry;
	var loadMoreButton;

	// -------------------------- -------------------------- //

	MD.index = function() {

		// ----- hero ----- //

		(function() {
			var hero = document.querySelector('#hero');
			heroContainer = hero.querySelector('.hero-masonry');
			heroMasonry = new Masonry(heroContainer, {
				itemSelector : '.hero-item',
				columnWidth : '.grid-sizer'
			});

			getExamples();

		})();

		loadMoreButton = document.querySelector('#load-more-examples');
		eventie.bind(loadMoreButton, 'click', getExamples);

	};

	var exampleOffset = 0;
	var isLoading = false;

	function getExamples() {
		// don't load more stuff if already loading
		if (isLoading) {
			return;
		}

		MD.notify('Loading Movies...');

		isLoading = true;
		// $.getJSON('http://zootool.com/api/users/items/?' +
		// 'username=desandro' +
		// '&apikey=8b604e5d4841c2cd976241dd90d319d7' +
		// '&tag=bestofmasonry' +
		// '&offset=' + exampleOffset +
		// '&callback=?'
		// )

		$.getJSON('https://imayampix.appspot.com/pixtime/movies').always(
				function() {
					isLoading = false;
				}).fail(getExamplesFail).done(getExamplesSuccess);
	}

	function getExamplesFail() {
		MD.notify('could not load examples :(', true);
	}

	function getExamplesSuccess(data) {
		// nothing more to load
		if (!data || !data.length) {
			loadMoreButton.style.display = 'none';
			MD.notify('No more Movies', true);
			return;
		}

		MD.hideNotify();
		exampleOffset += data.length;
		var items = [];
		var fragment = document.createDocumentFragment();
		for ( var i = 0, len = data.length; i < len; i++) {
			var item = makeExampleItem(data[i]);
			items.push(item);
			fragment.appendChild(item);
		}

		console.log(fragment);

		imagesLoaded(fragment).on('progress', function(imgLoad, image) {
			var item = image.img.parentNode.parentNode;
			// debugger
			// console.dir( image.img.parentNode );
			heroContainer.appendChild(item);
			heroMasonry.appended(item);
		});

	}
	//
	// function makeExampleItem( dataObj ) {
	// var item = document.createElement('div');
	// item.className = 'hero-item has-example is-hidden';
	// var link = document.createElement('a');
	// link.href = dataObj.url;
	// console.log(dataObj.url);
	// console.log(dataObj.image);
	// var img = document.createElement('img');
	// img.src = dataObj.image.replace('/l.', '/m.');
	// var title = document.createElement('p');
	// title.className = 'example-title';
	// title.textContent = dataObj.title;
	// 
	// title.textContent = '325 Ratings';
	// var title1 = document.createElement('p');
	// title1.textContent ="Kamalhassan, Saranya"
	// console.log('Title'+dataObj.title);
	// link.appendChild( img );
	// link.appendChild( title );
	// link.appendChild( title1);
	// item.appendChild( link );
	//  
	// console.log(item.toString());
	// return item;
	// }

	function makeExampleItem(dataObj) {
		var star = 'img/U0 copy.png';
		var onestar = 'img/U1 copy.png'
		var twostar = '/img/U2 copy.png';
		var threestar = 'img/U3 copy.png';
		var fourstar = 'img/U4 copy.png';
		var fivestar = 'img/U5 copy.png';
		
		var item = document.createElement('div');
		item.className = 'hero-item has-example is-hidden';
		item.id = 'movie';
		
		var link = document.createElement('a');
		link.href = dataObj.movieurl;
		link.href = 'http://localhost:8889/pixtime/movies/8d16463d-1649-459a-89f6-1e996cd846c4';
		console.log(dataObj.movieurl);
		
		var img = document.createElement('img');
		img.title = dataObj.movieName;
		img.src = 'data:image/jpeg;base64,' + dataObj.encodedImg;

		var title = document.createElement('p');
		title.className = 'example-title';
		title.textContent = '' + dataObj.movieName;

		var el = document.createElement('div');
		el.id = 'hidden';
		el.textContent = 'http://localhost:8888/pixtime/movies/1d665dd1-1d17-49e4-9430-c0fbf6654e82';
		console.log('Title' + dataObj.title);

		var img1 = document.createElement('img');
		img1.title = "Rate it !!";
		img1.textContent = dataObj.current_Star_Rate;
		 if(img1.textContent=="0")
        {
		img1.src = star;
		img1.className = 'startest';
		item.appendChild(img1);
        }
		 else if(img1.textContent=="1")
			{
			 img1.src = onestar;
				img1.className = 'startest';
				item.appendChild(img1);
			}
		 else if(img1.textContent=="2")
			{	
			 img1.src = twostar;
				img1.className = 'startest';
				item.appendChild(img1);
				
			}
		else if(img1.textContent=="3")
			{	
			img1.src = threestar;
			img1.className = 'startest';
			item.appendChild(img1);
			}
			else if(img1.textContent=="4")
				{	
				img1.src = fourstar;
				img1.className = 'startest';
				item.appendChild(img1);
					
				}
			else if(img1.textContent=="5")
			{	
			img1.src = fivestar;
			img1.className = 'startest';
			item.appendChild(img1);
				
			}


		var desc = document.createElement('p');
		desc.className = 'description';
		desc.textContent = dataObj.description;
		console.log('Description'+dataObj.desc);
		

		//link.appendChild(title1);
		

		link.appendChild(img);
		// link.appendChild(title2);

		link.appendChild(title);
		//link.appendChild(desc);
		item.appendChild(link);
		// item.appendChild(el);

		console.log(item.toString());

		return item;
	}
	
})(window);
