package com.baidu.push.login.l_activity;

import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.CancellationSignal;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.baidu.push.login.R;
import com.baidu.push.login.bean.LoaginBean;
import com.baidu.push.login.contract.LoginContract;
import com.baidu.push.login.presenter.LoginPresenter;
import com.baidu.push.mymodularizationforone.MainActivity;
import com.baidu.push.ommon_base.base.BaseMVPActivity;

import butterknife.BindView;

@Route(path = "/login/login")
public class LoginActivity extends BaseMVPActivity<LoginPresenter> implements LoginContract.View {

    private static final String TAG = LoginActivity.class.getSimpleName();
    @BindView(R.id.login_username)
    EditText loginUsername;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_btn)
    Button loginBtn;

    private BiometricPrompt biometricPrompt;
    private CancellationSignal mCancellationSignal;
    private BiometricPrompt.AuthenticationCallback authenticate;

    @Override
    protected LoginPresenter creatPresenter() {
        return new LoginPresenter();
    }


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void initView() {
        presenter.attachView(this);
        loginBtn.setOnClickListener(v -> presenter.getLoginData(loginUsername.getText().toString(), loginPassword.getText().toString()));
        //系统验证
        //Builder 收集要在系统提供的生物识别对话框中显示的参数的构建器
        biometricPrompt = new BiometricPrompt.Builder(this)
                .setTitle("指纹验证")//设置要显示的标题。
                .setDescription("简要描述")
                //setNegativeButton(text: CharSequence,
                // executor: Executor, listener: DialogInterface.OnClickListener)
                //必需：设置否定按钮的文本。
                //CharSequence
                .setNegativeButton("密码登录", getMainExecutor(), (dialogInterface, i) -> Log.e(TAG, "点击取消")).build();
        mCancellationSignal = new CancellationSignal();
        mCancellationSignal.setOnCancelListener(() -> {//取消操作并向取消侦听器发出信号。
            Log.e(TAG, "Canceled");
        });
        authenticate = new BiometricPrompt.AuthenticationCallback() {
            //遇到不可恢复的错误并且操作完成时调用。
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Log.e(TAG, "onAuthenticationError:" + errorCode);
                Log.e(TAG, "onAuthenticationError:" + errString);

            }

            //在身份验证期间遇到可恢复错误时调用。
            @Override
            public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                super.onAuthenticationHelp(helpCode, helpString);
                Log.e(TAG, "onAuthenticationHelp:");
            }

            //在识别生物识别时调用。
            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Log.e(TAG, "onAuthenticationSucceeded: ????????????????????");
//                ARouter.getInstance()
////                        .build("/app/MainActivity")
////                        .withInt("error_code", 1)
////                        .navigation();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

            }

            //当生物特征有效但无法识别时调用。
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Log.e(TAG, "onAuthenticationFailed:");
            }
        };
        biometricPrompt.authenticate(mCancellationSignal, getMainExecutor(), authenticate);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void loginSuccess(LoaginBean result) {


    }

    @Override
    public void onError(Throwable throwable) {

    }
}
