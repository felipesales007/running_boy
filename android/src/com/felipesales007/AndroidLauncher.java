package com.felipesales007;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.appodeal.gdx.GdxAppodeal;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.felipesales007.chamadas.Telas;

public class AndroidLauncher extends AndroidApplication
{
	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useImmersiveMode = true;
		initialize(new Telas(), config);

		GdxAppodeal.disableNetwork("cheetah");
		String appKey = "7056433c9538964706b5f6e02fb37ae8072f69473f33e149";
		GdxAppodeal.disableLocationPermissionCheck();
		GdxAppodeal.initialize(appKey, GdxAppodeal.BANNER);
		//GdxAppodeal.setTesting(true); // < -- HABILITA O MODO TESTE DE PROPAGANDA
		GdxAppodeal.show(GdxAppodeal.BANNER_TOP);
		GdxAppodeal.isLoaded(GdxAppodeal.BANNER);
	}

	private long lastBackPressTime;
	private Toast toast;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (event.getAction() == KeyEvent.ACTION_DOWN)
		{
			switch (keyCode)
			{
				case KeyEvent.KEYCODE_BACK:
					if (this.lastBackPressTime < System.currentTimeMillis() - 4000)
					{
						toast = Toast.makeText(this, "Pressione novamente para fechar", Toast.LENGTH_LONG);
						toast.show();
						this.lastBackPressTime = System.currentTimeMillis();
					}
					else
					{
						if (toast != null)
						{
							toast.cancel();
						}
						super.onBackPressed();
					}
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
}