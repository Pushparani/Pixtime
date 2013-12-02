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
		var item = document.createElement('div');
		item.className = 'hero-item has-example is-hidden';
		var link = document.createElement('a');
		link.href = dataObj.movieurl;
		console.log(dataObj.movieurl);
		var img = document.createElement('img');

		img.src = 'data:image/jpeg;base64,' + dataObj.encodedImg;

		var title = document.createElement('p');
		title.className = 'example-title';
		title.textContent = dataObj.movieName;

		// title.textContent = '325 Ratings';
		// var title1 = document.createElement('p');
		// title1.textContent ="Kamalhassan, Saranya"
		console.log('Title' + dataObj.title);
		link.appendChild(img);
		link.appendChild(title);
		// link.appendChild( title1);
		item.appendChild(link);

		console.log(item.toString());

		// var encImg = 'data:image/jpeg;base64,' + dataObj.encodedImg;
		// var caption = dataObj.movieName;
		// var date = '2013-11-20';
		// var figure = '<figure class="span3 picture-item" data-date-created='
		// + date
		// + ' data-groups=\'["photography"]\'><img src='
		// + encImg
		// + '><div class="picture-item__details"><figcaption
		// class="picture-item__title">'
		// + caption
		// + '</figcaption><p
		// class="picture-item__tags">photography</p></div></figure>';
		//
		// var item = document.createElement('div');
		// item.className = 'hero-item has-example is-hidden';
		// item.id = 'grid';
		return item;
	}

})(window);