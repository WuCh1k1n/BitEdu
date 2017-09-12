/*折线图组件对象*/

var H5ComponentPolyline = function(name, cfg) {
	var component = new H5ComponentBase(name, cfg);

	var w = cfg.width;
	var h = cfg.height;

	var cns = document.createElement('canvas');
	var ctx = cns.getContext('2d');
	cns.width = ctx.width = w;
	cns.height = ctx.height = h;
	component.append(cns);
	window.ctx = ctx;

	var step = 10;
	ctx.lineWidth = 1;
	ctx.strokeStyle = "#AAAAAA";
	ctx.beginPath();
	
	//画水平网格线
	for(var i = 0; i <= step; i++) {
		var y = (h / step) * i;
		ctx.moveTo(0, y);
		ctx.lineTo(w, y);
	}

	//画垂直网格线
	step = cfg.data.length;
	var text_w = w / (step + 1) >> 0;
	for(var i = 0; i <= cfg.data.length + 1; i++) {
		var x = (w / (step + 1)) * i;
		ctx.moveTo(x, 0);
		ctx.lineTo(x, h);

		//输出横坐标名称
		if(cfg.data[i]) {
			var sec = 1.5 + i * 0.2;
			var text = $('<div class="text" style="-webkit-transition:all 1s ' + sec + 's">');
			text.text(cfg.data[i][0]);
			text.css('width', text_w / 2).css('left', x / 2 + text_w / 4);
			component.append(text);
		}
	}

	ctx.stroke();

	//绘制折线数据
	var cns = document.createElement('canvas');
	var ctx = cns.getContext('2d');
	cns.width = ctx.width = w;
	cns.height = ctx.height = h;
	component.append(cns);

	var draw = function(per) {
		//避免生长动画时重叠，清空画布
		ctx.clearRect(0, 0, w, h);

		ctx.lineWidth = 3;
		ctx.strokeStyle = "#ff8878";
		ctx.beginPath();

		//画点
		var x = 0;
		var y = 0;
		for(var i = 0; i < cfg.data.length; i++) {
			var item = cfg.data[i];
			x = (w / (cfg.data.length + 1)) * (i + 1);
			y = h - (item[1] * h * per);

			ctx.moveTo(x, y);
			ctx.arc(x, y, 5, 0, 2 * Math.PI);
		}

		//连线
		ctx.moveTo((w / (cfg.data.length + 1)), h - (cfg.data[0][1] * h * per));
		for(var i = 0; i < cfg.data.length; i++) {
			var item = cfg.data[i];
			x = (w / (cfg.data.length + 1)) * (i + 1);
			y = h - (item[1] * h * per);

			ctx.lineTo(x, y);
		}
		ctx.stroke();

		//绘制阴影
		ctx.strokeStyle = "rgba(255,255,255,0)";
		ctx.lineTo(x, h);
		ctx.lineTo(w / (cfg.data.length + 1), h);
		ctx.fillStyle = "rgba(255,136,120,0.2)";
		ctx.fill();

		//写数据
		for(var i = 0; i < cfg.data.length; i++) {
			var item = cfg.data[i];
			x = (w / (cfg.data.length + 1)) * (i + 1);
			y = h - (item[1] * h * per);

			ctx.font="20px Calibri";
			ctx.fillStyle = item[3] ? item[3] : '#595959';
			ctx.fillText(item[2] , x - 10, y - 10);
		}
		ctx.stroke();
	}

	//折线图生长动画
	component.on('onLoad', function() {
		var s = 0;
		for(i = 0; i < 100; i++) {
			setTimeout(function() {
				s += 0.01;
				draw(s);
			}, i * 10 + 500);
		}
	});

	//折线图退场动画
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