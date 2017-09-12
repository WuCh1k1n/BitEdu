/*雷达图组件对象*/

var H5ComponentRadar = function(name, cfg) {
	var component = new H5ComponentBase(name, cfg);

	var w = cfg.width;
	var h = cfg.height;

	var cns = document.createElement('canvas');
	var ctx = cns.getContext('2d');
	cns.width = ctx.width = w;
	cns.height = ctx.height = h;
	component.append(cns);
	window.ctx = ctx;

	var r = w / 2;
	//圆心为（a，b）
	var a = r;
	var b = r;
	var step = cfg.data.length;

	//绘制网格背景层，分成s份绘制
	var isBlue = false;
	ctx.strokeStyle = "rgba(0,0,0,0)";

	for(var s = 10; s > 0; s--) {
		ctx.beginPath();
		for(var i = 0; i < step; i++) {
			var rad = (2 * Math.PI / 360) * (360 / step) * i;
			var x = a + Math.sin(rad) * r * (s / 10);
			var y = b + Math.cos(rad) * r * (s / 10);
			ctx.lineTo(x, y);
		}
		ctx.closePath();
		ctx.fillStyle = (isBlue = !isBlue) ? '#99c0ff' : '#ffffff';
		ctx.fill();
		ctx.stroke();
	}

	//绘制伞骨
	ctx.strokeStyle = '#e0e0e0';
	for(var i = 0; i < step; i++) {
		var rad = (2 * Math.PI / 360) * (360 / step) * i;
		var x = a + Math.sin(rad) * r;
		var y = b + Math.cos(rad) * r;
		ctx.moveTo(a, b);
		ctx.lineTo(x, y);

		//输出项目文字
		var text = $('<div class="text">');
		text.text(cfg.data[i][0]);

		if(x > w / 2) {
			text.css('left', x / 2 + 5);
		} else {
			text.css('right', (w - x) / 2 + 5);
		}

		if(y > h / 2) {
			text.css('top', y / 2 + 5);
		} else {
			text.css('bottom', (h - y) / 2 + 5);
		}

		if(cfg.data[i][2]) {
			text.css('color', cfg.data[i][2]);
		}
		
		component.append(text);
	}
	ctx.stroke();

	//绘制数据层
	var cns = document.createElement('canvas');
	var ctx = cns.getContext('2d');
	cns.width = ctx.width = w;
	cns.height = ctx.height = h;
	component.append(cns);
	window.ctx = ctx;

	//绘制数据折线
	ctx.strokeStyle = '#f00';
	var draw = function(per) {
		ctx.clearRect(0, 0, w, h);
		for(i = 0; i < step; i++) {
			var rad = (2 * Math.PI / 360) * (360 / step) * i;
			var rate = cfg.data[i][1] * per;
			var x = a + Math.sin(rad) * r * rate;
			var y = b + Math.cos(rad) * r * rate;
			ctx.lineTo(x, y);
		}
		ctx.closePath();
		ctx.stroke();

		//绘制数据圆点
		ctx.fillStyle = '#ff7676';
		for(i = 0; i < step; i++) {
			var rad = (2 * Math.PI / 360) * (360 / step) * i;
			var rate = cfg.data[i][1] * per;
			var x = a + Math.sin(rad) * r * rate;
			var y = b + Math.cos(rad) * r * rate;
			ctx.beginPath();
			ctx.arc(x, y, 5, 0, 2 * Math.PI);
			ctx.fill();
			ctx.closePath();
		}
	}

	//雷达图生长动画
	component.on('onLoad', function() {
		var s = 0;
		for(i = 0; i < 100; i++) {
			setTimeout(function() {
				s += 0.01;
				draw(s);
			}, i * 10 + 500);
		}
	});

	//雷达图退场动画
	component.on('onLeave', function() {
		var s = 1;
		for(i = 0; i < 100; i++) {
			setTimeout(function() {
				s -= 0.01;
				draw(s);
			}, i * 10);
		}
	});

	return component;
}