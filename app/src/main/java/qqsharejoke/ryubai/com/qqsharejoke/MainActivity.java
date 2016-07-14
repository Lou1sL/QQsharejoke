package qqsharejoke.ryubai.com.qqsharejoke;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;


public class MainActivity extends ActionBarActivity {

    private static final String APPID = "1105440081";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        //创建实例，这是SDK所有功能的entrance
        Tencent mTencent = Tencent.createInstance(APPID, this.getApplicationContext());

        onClickShare(mTencent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private void onClickShare(Tencent mTencent) {
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "n");
        //params.putString(QQShare.SHARE_TO_QQ_SUMMARY,  "要分享的摘要");
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,  "about:blank");
        //params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,"http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");
        //params.putString(QQShare.SHARE_TO_QQ_APP_NAME,  "666");


        //params.putInt(QQShare.SHARE_TO_QQ_EXT_INT,  "其他附加功能");
        mTencent.shareToQQ(MainActivity.this, params, new BaseUiListener());
    }
}

    class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {

            JSONObject jsonresponse =(JSONObject)response;
            //mBaseMessageText.setText("onComplete:");
            //mMessageText.setText(response.toString());
            doComplete(jsonresponse);
        }



    protected void doComplete(JSONObject values) {
    }
    @Override
    public void onError(UiError e) {
        //showResult("onError:", "code:" + e.errorCode + ", msg:"+ e.errorMessage + ", detail:" + e.errorDetail);
    }
    @Override
    public void onCancel() {
        //showResult("onCancel", "");
    }
}
