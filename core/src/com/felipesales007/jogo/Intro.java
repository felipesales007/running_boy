package com.felipesales007.jogo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.felipesales007.chamadas.BaseTela;
import com.felipesales007.chamadas.Importacao;
import com.felipesales007.chamadas.Telas;

import static java.lang.String.valueOf;

public class Intro extends BaseTela
{
    Importacao i = new Importacao();

    public Intro(Telas jogo)
    {
        super(jogo);
    }

    @Override
    public void show()
    {
        i.batch = new SpriteBatch();
        i.intro = new Texture[27];
        i.intro[0] = new Texture("introLogo/1.png");
        i.intro[1] = new Texture("introLogo/2.png");
        i.intro[2] = new Texture("introLogo/3.png");
        i.intro[3] = new Texture("introLogo/4.png");
        i.intro[4] = new Texture("introLogo/5.png");
        i.intro[5] = new Texture("introLogo/6.png");
        i.intro[6] = new Texture("introLogo/7.png");
        i.intro[7] = new Texture("introLogo/8.png");
        i.intro[8] = new Texture("introLogo/9.png");
        i.intro[9] = new Texture("introLogo/10.png");
        i.intro[10] = new Texture("introLogo/11.png");
        i.intro[11] = new Texture("introLogo/12.png");
        i.intro[12] = new Texture("introLogo/13.png");
        i.intro[13] = new Texture("introLogo/14.png");
        i.intro[14] = new Texture("introLogo/15.png");
        i.intro[15] = new Texture("introLogo/16.png");
        i.intro[16] = new Texture("introLogo/17.png");
        i.intro[17] = new Texture("introLogo/18.png");
        i.intro[18] = new Texture("introLogo/19.png");
        i.intro[19] = new Texture("introLogo/20.png");
        i.intro[20] = new Texture("introLogo/21.png");
        i.intro[21] = new Texture("introLogo/22.png");
        i.intro[22] = new Texture("introLogo/23.png");
        i.intro[23] = new Texture("introLogo/24.png");
        i.intro[24] = new Texture("introLogo/25.png");
        i.intro[25] = new Texture("introLogo/26.png");
        i.intro[26] = new Texture("introLogo/27.png");

        i.introVoz = Gdx.audio.newSound(Gdx.files.internal("sons/introVoz.wav"));
        i.introSom = Gdx.audio.newMusic(Gdx.files.internal("sons/introSom.wav"));
        i.introSom.setVolume(0.8f);
        i.introNome = new BitmapFont(Gdx.files.internal("fontes/fontePrincipal.fnt"), Gdx.files.internal("fontes/fontePrincipal.png"), false);

        i.larguraDispositivo = Gdx.graphics.getWidth();
        i.alturaDispositivo = Gdx.graphics.getHeight();
        i.introNome.getData().setScale(Float.parseFloat(valueOf(i.alturaDispositivo / 600)));
    }

    @Override
    public void hide()
    {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose()
    {
        super.dispose();
        i.batch.dispose();
        i.introVoz.dispose();
        i.introSom.dispose();
        i.introNome.dispose();
        jogo.dispose();
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        i.deltaTime = Gdx.graphics.getDeltaTime();

        i.tempo = i.tempo + 1;
        if (i.tempo > 10)
        {
            i.variacaoIntro += i.deltaTime * 20;
            if (i.variacaoIntro > 27 && i.introducaoVoz == 0)
            {
                i.variacaoIntro = 1;
                i.introducaoVoz = 1;
                i.introVoz.play(1);
            }
        }

        i.batch.begin();
        if (i.introducaoVoz == 0)
        {
            i.introSom.play();
            i.batch.draw(i.intro[(int) i.variacaoIntro], 0, 0, i.larguraDispositivo, i.alturaDispositivo);
        }
        else
        {
            i.batch.draw(i.intro[26], 0, 0, i.larguraDispositivo, i.alturaDispositivo);
            i.introNome.draw(i.batch, "Grape's Group", i.alturaDispositivo / 2, i.larguraDispositivo / 9.5f);
            i.tempoIntro = i.tempoIntro + 1;
            if (i.tempoIntro > 125)
            {
                jogo.setScreen(jogo.menuJogo);
            }
        }
        i.batch.end();
    }
}