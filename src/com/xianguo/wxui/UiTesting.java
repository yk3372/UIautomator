package com.xianguo.wxui;

import java.util.ArrayList;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class UiTesting extends UiAutomatorTestCase {
	public static int WAIT_TIME = 15000;
	public static int WAIT_SHORT_TIME = 15000;

	public void testDemo() throws UiObjectNotFoundException {
        //只点击一项
        UiObject gridview = new UiObject(new UiSelector().resourceId("com.tencent.mm:id/gridpaper_gridview"));
        UiObject itemViewObject = gridview.getChild(new UiSelector().className("android.widget.FrameLayout").index(0));
        itemViewObject.clickAndWaitForNewWindow();
        recordeItemOpr();
	}

    /**
     * 循环遍历所有的频道，之前用在公众帐号
     * @throws UiObjectNotFoundException
     */
    public void recordAccountByFor()throws UiObjectNotFoundException{
        UiScrollable uiCollection = new UiScrollable(new UiSelector().resourceId("com.tencent.mm:id/gridpaper_flipper"));

        UiObject gridviewDot = new UiObject(new UiSelector().resourceId("com.tencent.mm:id/gridpaper_dot"));
        int pageTotalNum = gridviewDot.getChildCount();

        for (int i = 0; i < pageTotalNum; i++) {
            uiCollection.setAsHorizontalList().flingBackward();
        }

        startRecordeAllAccount();

        for (int i = 1; i < pageTotalNum; i++) {
            uiCollection.setAsHorizontalList().flingForward();
            startRecordeAllAccount();
        }
    }

	private void startRecordeAllAccount() {
		try {
			UiObject gridview = new UiObject(new UiSelector().resourceId("com.tencent.mm:id/gridpaper_gridview"));
			for (int i = 0; i < gridview.getChildCount(); i++) {
				UiObject itemViewObject = gridview.getChild(new UiSelector().className("android.widget.FrameLayout").index(i));
				itemViewObject.clickAndWaitForNewWindow();
				recordeItemOpr();
			}
		} catch (Exception exception) {
		}
		
	}
	
	private void recordeItemOpr(){
        clickImageViewByResId("com.tencent.mm:id/title_btn1");// 点击个人信息
		if(clickByTextShort("View history")){
            if (clickImageViewByResIdAndTry("com.tencent.mm:id/title_btn1")) {// 点击更多
                if (!clickByTextShort("Copy URL")) {
                    getUiDevice().pressBack();
                }
            }
//            else{
//                clickByTextShort("Back");
//                clickByTextShort("View history");
//                if (clickImageViewByResId("com.tencent.mm:id/title_btn1")) {// 点击更多
//                    if (!clickByTextShort("Copy URL")) {
//                        getUiDevice().pressBack();
//                    }
//                }
//            }
            clickByTextShort("Back");
        }
		clickByTextShort("Back");
		clickByTextShort("Back");
	}

	
	@SuppressWarnings("unused")
	private void startApp() throws UiObjectNotFoundException {
		ArrayList<String> accounts = AccountsUtils.getAllAccounts();
		int len = accounts.size();
		for (int i = 0; i < len; i++) {
			startRecordeAccountByName(accounts.get(i));
		}

	}

	private void startRecordeAccountByName(String title) {
		try {
			UiObject history = new UiObject(new UiSelector().resourceId("com.tencent.mm:id/tmessage_lv"));
			UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(history.isScrollable()));
			// UiObject startApp = appViews.getChildByText(new
			// UiSelector().className(android.widget.TextView.class.getName()),
			// title,history.isScrollable());
			UiObject startApp1 = appViews.getChildByText(new UiSelector().resourceId("com.tencent.mm:id/nickname_tv"), title, history.isScrollable());
			startApp1.clickAndWaitForNewWindow(WAIT_TIME);
		} catch (UiObjectNotFoundException e) {
			return;
		}

		clickImageViewByResId("com.tencent.mm:id/title_btn1");// 点击个人信息
		clickByTextShort("View history");
		if (clickImageViewByResId("com.tencent.mm:id/title_btn1")) {// 点击更多
			if (!clickByTextShort("Copy URL")) {
				getUiDevice().pressBack();
			}
		}

		clickByTextShort("Back");
		clickByTextShort("Back");
		clickByTextShort("Back");
		//
		// getUiDevice().pressBack();
		// getUiDevice().pressBack();
		// getUiDevice().pressBack();
	}

	public void clickViewByIndex(int index) throws UiObjectNotFoundException {
		UiObject object = new UiObject(new UiSelector().index(index));
		object.clickAndWaitForNewWindow(WAIT_TIME);
	}

	public boolean clickImageViewByResIdAndTry(String resId) {
		try {
			UiObject history = new UiObject(new UiSelector().resourceId(resId));
			int tryNum = 0;
			boolean flag = history.clickAndWaitForNewWindow(WAIT_TIME);
			while (!flag && tryNum < 30) {
				tryNum++;
				getUiDevice().waitForIdle(5000);
                System.out.println("try: "+tryNum);
				flag = history.clickAndWaitForNewWindow(WAIT_TIME);
			}
			return flag;
		} catch (UiObjectNotFoundException e) {
			return false;
		}
	}

    public boolean clickImageViewByResId(String resId) {
        try {
            UiObject history = new UiObject(new UiSelector().resourceId(resId));
            return history.clickAndWaitForNewWindow(WAIT_TIME);
        } catch (UiObjectNotFoundException e) {
            return false;
        }
    }

	public void longClickByText(String text) throws UiObjectNotFoundException {
		UiObject object = new UiObject(new UiSelector().text(text));
		object.longClick();
	}

	public void longClickByStartText(String text) throws UiObjectNotFoundException {
		UiObject object = new UiObject(new UiSelector().textStartsWith(text));
		object.longClick();
	}

	public void setTextByText(String test, String args) throws UiObjectNotFoundException {
		UiObject object = new UiObject(new UiSelector().text(test));
		object.setText(args);
	}

	public void setTextByIndex(int index, String args) throws UiObjectNotFoundException {
		UiObject object = new UiObject(new UiSelector().index(index));
		object.setText(args);
	}

	public void clickByDescription(String text) throws UiObjectNotFoundException {
		UiObject object = new UiObject(new UiSelector().description(text));
		object.clickAndWaitForNewWindow(WAIT_TIME);
	}

	public boolean clickByText(String text) {
		try {
			UiObject object = new UiObject(new UiSelector().text(text));
			return object.clickAndWaitForNewWindow(WAIT_TIME);
		} catch (UiObjectNotFoundException e) {
			return false;
		}
	}

	public boolean clickByTextShort(String text) {
		try {
			UiObject object = new UiObject(new UiSelector().text(text));
			return object.clickAndWaitForNewWindow(WAIT_SHORT_TIME);
		} catch (UiObjectNotFoundException e) {
			return false;
		}
	}

	public void clickByStartText(String text) throws UiObjectNotFoundException {
		UiObject object = new UiObject(new UiSelector().textStartsWith(text));
		object.clickAndWaitForNewWindow(WAIT_TIME);
	}

	public void clickByIndex(int index) throws UiObjectNotFoundException {
		UiObject object = new UiObject(new UiSelector().index(index));
		object.clickAndWaitForNewWindow(WAIT_TIME);
	}
}
