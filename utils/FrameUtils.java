package utils;


import java.util.HashMap;
import java.util.Map;

import window.LoginView;
import window.MainView;
import window.PrivateChatView;
import window.RegisterView;
import window.SearchView;
import window.ServerView;

/**
 * 这个工具类缓存所有在客户端打开过的页面，以便于需要的时候使用
 * 
 * @author UEK-N
 *
 */
public class FrameUtils {
	
	public static LoginView loginView;
	
	public static RegisterView registerView;
	
	public static SearchView searchView;
	public static ServerView serverView;
	
	public static MainView mainView;
	
	//缓存所有打开的聊天界面
	public static Map<Integer , PrivateChatView> friendIdAndPrivateChatViews = new HashMap<>();

	//缓存所有打开的群聊界面
	public static Map<Integer , PrivateChatView> crowdIdAndPrivateChatViews = new HashMap<>();
}


