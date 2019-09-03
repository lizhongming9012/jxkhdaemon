Ext.define('Jxgl.failure.FailureProcess', {
			singleton : true,
			alternateClassName : 'FailureProcess',
			/**
			 * success在去请求的状态码为200到300（不包含300）或304才会触发，而failure则与suceess对应使用
			 * @param {}
			 *            response
			 * @param {}
			 *            options
			 */
			ajax : function(response, options) {
				if (response.status == 404) {
					Ext.Msg.alert('错误信息', '错误的请求地址。');
				} else if (response.status == 500) {
					Ext.Msg.alert('错误信息', '服务器内部错误。');
				} else {
					Ext.Msg.alert('错误信息', Ext.String.format(
									'错误代码：{0}<br\>响应：{1}', response.status,
									response.responseText));
				}
			},
			/**
			 * 代理异常处理存储的load方法，sync方法时返回异常。只有返回数据success的属性为true才执行success的回调
			 * 当success为false时，服务器返回的错误信息转换为options对象的error属性的值
			 * (Ext.data.proxy.Ajax exception中监听)
			 * 
			 * @param {}
			 *            proxy
			 * @param {}
			 *            response
			 * @param {}
			 *            options
			 * @param {}
			 *            eOpts
			 */
			proxy : function(proxy, response, options, eOpts) {
				var status = response.status;
				if ((status >= 200 && status < 300) || status == 304) {
					Ext.Msg.alert('信息', options.error);
				} else {
					FailureProcess.ajax(response, options);
				}
			},
			/**
			 * 在服务器端返回success属性为false时，执行failure定义的回调函数（Ext.form.Basic
			 * actionfailed事件中监听）。当返回erros属性时，说明字段有错误，会自动在对应的字段提示错误
			 * 当没有返回errors属性时，只返回msg属性，说明字段没有错误，是其他错误
			 * 
			 * @param {}
			 *            form
			 * @param {}
			 *            action
			 */
			form : function(form, action) {
				var status = action.response.status;
				if ((status >= 200 && status < 300) || status == 304) {
					if (action.result.errors === undefined) {
						Ext.Msg.alert('信息', action.result.msg);
					}
				} else {
					FailureProcess.ajax(action.response, form);
				}
			}
		});