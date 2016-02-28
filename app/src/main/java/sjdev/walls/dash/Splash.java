package sjdev.walls.dash;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Splash extends Activity
{
    View.OnClickListener mOnClickListener;
    protected boolean _active = true;
    protected int _splashTime = 2 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Thread splashTread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    int waited = 0;
                    while (_active && (waited < _splashTime))
                    {
                        sleep(100);
                        if (_active)
                        {
                            waited += 100;
                        }
                    }
                }
                catch (InterruptedException e)
                {

                }
                finally
                {
                    finish();
                    if (Util.hasNetwork(Splash.this)) {
                        Intent i = new Intent(Splash.this, MainActivity.class);
                        startActivity(i);
                    } else {
                        nonet();
                    }
                }
            }
        };
        splashTread.start();
    }
    private void nonet()
    {
        Intent nonet=new Intent(getApplicationContext(),NoNet.class);
        startActivity(nonet);
        finish();
    }
}
