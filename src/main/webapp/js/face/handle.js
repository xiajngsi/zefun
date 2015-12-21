(function() {
	var i = 0,
		lastTime = 0,
		vendors = ['ms', 'moz', 'webkit', 'o'];
	
	while (i < vendors.length && !window.requestAnimationFrame) {
		window.requestAnimationFrame = window[vendors[i] + 'RequestAnimationFrame'];
		i++;
	}
	
	if (!window.requestAnimationFrame) {
		window.requestAnimationFrame = function(callback, element) {
			var currTime = new Date().getTime(),
				timeToCall = Math.max(0, 1000 / 60 - currTime + lastTime),
				id = setTimeout(function() { callback(currTime + timeToCall); }, timeToCall);
			
			lastTime = currTime + timeToCall;
			return id;
		};
	}
}());
var api = new FacePP('2bc8f009c414c660aaf3867c9280ab5f', 'WwOmCASLk3s6ktFtDewS17Lr1fjUe1Ts');
var App = {
	start: function(stream) {
		console.log("start ...");
		App.video.addEventListener('canplay', function() {
			App.video.removeEventListener('canplay');
			checkFaceLoop = window.setTimeout(function(){
				console.log("drawing ...");

				App.video.play();
				App.canvas.style.display = 'inline';
				App.info.style.display = 'none';
				App.canvas.width = App.video.videoWidth;
				App.canvas.height = App.video.videoHeight;
				App.backCanvas.width = App.video.videoWidth / 4;
				App.backCanvas.height = App.video.videoHeight / 4;
				App.backContext = App.backCanvas.getContext('2d');

				var w = 300 / 4 * 0.8,
					h = 270 / 4 * 0.8;

				App.comp = [{
					x: (App.video.videoWidth / 4 - w) / 2,
					y: (App.video.videoHeight / 4 - h) / 2,
					width: w, 
					height: h,
				}];

				App.drawToCanvas();
			}, 1500);
		}, true);
		
		var domURL = window.URL || window.webkitURL;
		App.video.src = domURL ? domURL.createObjectURL(stream) : stream;
	},
	denied: function() {
		console.log("denied ...");
		App.info.innerHTML = 'Camera access denied!<br>Please reload and try again.';
	},
	error: function(e) {
		console.log("error ...");
		dialog(e);
		if (e) {
			console.error(e);
		}
		App.info.innerHTML = 'Please go to about:flags in Google Chrome and enable the &quot;MediaStream&quot; flag.';
	},
	drawToCanvas: function() {
		requestAnimationFrame(App.drawToCanvas);
		var video = App.video,
			ctx = App.context,
			backCtx = App.backContext,
			m = 4,
			w = 4,
			i,
			comp;
		
		ctx.drawImage(video, 0, 0, App.canvas.width, App.canvas.height);
		
		backCtx.drawImage(video, 0, 0, App.backCanvas.width, App.backCanvas.height);
	}
};
var y = function(c) {
    var b, a, p, h, e;
    p = c.split(",")[0].split(":")[1].split(";")[0];
    b = atob(c.split(",")[1]);
    c = [];
    a = h = 0;
    for (e = b.length; 0 <= e ? h < e : h > e; a = 0 <= e ? ++h : --h)
        c.push(b.charCodeAt(a));
    return new Blob([new Uint8Array(c)], {type: p})
};

App.init = function() {
	App.video = document.createElement('video');
	App.backCanvas = document.createElement('canvas');
	App.canvas = document.querySelector('#output');
	App.canvas.style.display = 'none';
	App.context = App.canvas.getContext('2d');
	App.info = document.querySelector('#info');
	
	navigator.getUserMedia_ = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia;
	
	try {
		navigator.getUserMedia_({
			video: true,
			audio: false
		}, App.start, App.denied);
	} catch (e) {
		dialog(e);
		try {
			navigator.getUserMedia_('video', App.start, App.denied);
		} catch (e) {
			App.error(e);
		}
	}
	
	App.video.loop = App.video.muted = true;
	App.video.load();
};

App.init();
function capture(){
	comp = ccv.detect_objects(App.ccv = App.ccv || {
		canvas: App.backCanvas,
		cascade: cascade,
		interval: 5,
		min_neighbors: 1
	});
	
	if (!comp.length) {
		return null;
	}
	var canvas2 = document.createElement("canvas");
    canvas2.width = App.video.videoWidth;
    canvas2.height = App.video.videoHeight;
    canvas2.getContext('2d').drawImage(App.video, 0, 0, canvas2.width, canvas2.height);
    
    var k = {img: y(canvas2.toDataURL()),_post: !0};
    k["mode"] = "oneface";
    return k;
}