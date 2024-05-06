码表(wx_qr_code)
id,scene_code,view_url,page_url,open_id,u_id,user_bind_time,cretaed

用户表(user_info)
id,open_id,qrcode_id,scene_code,wx_name,mobile_phone,wx_notice_flag,sms_notice_flag,tel_notice_flag,car_num,new_energy_flag,scan_notice,created


码表浏览记录(wx_qrcode_view_his)
id,scene_code,view_open_id,created


微信消息表(wx_msg)
id,scene_code,from_user_id,from_user_openid,to_user_id,to_user_openid,template_id,template_data,send_status,created


微信回复消息表(wx_reply_msg)
id,wx_msg_id,scene_code,from_user_id,from_user_openid,to_user_id,to_user_openid,template_id,template_data,send_status,created


短信通知表(sms_notice)
id,scene_code,from_user_id,to_user_id,to_mobile,msg_content,send_status,created


电话通知表(tel_notice)
id,scene_code,from_user_id,to_user_id,send_status,created


