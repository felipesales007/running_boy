package com.felipesales007.jogo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.felipesales007.chamadas.BaseTela;
import com.felipesales007.chamadas.Importacao;
import com.felipesales007.chamadas.Telas;

import static java.lang.String.valueOf;

public class Menu extends BaseTela
{
    Importacao i = new Importacao();

    public Menu(final Telas jogo)
    {
        super(jogo);
        i.stage = new Stage(new FitViewport(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4));
        i.skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        i.play = new TextButton("Play", i.skin);

        i.play.addCaptureListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                i.musicaMenu.pause();
                i.clique.play(1);
                jogo.setScreen(jogo.playJogo);
            }
        });

        i.larguraDispositivo = Gdx.graphics.getWidth();
        i.alturaDispositivo = Gdx.graphics.getHeight();

        i.play.getLabel().setFontScale(i.larguraDispositivo / 1500, i.alturaDispositivo / 1500);
        i.play.setSize(i.larguraDispositivo / 20, i.alturaDispositivo / 20);
        i.play.setPosition(i.larguraDispositivo / 10, i.alturaDispositivo / 13);
        i.play.setColor(Color.GOLD);
        i.stage.addActor(i.play);
    }

    @Override
    public void show()
    {
        try
        {
            FileHandle fileo = Gdx.files.local("melhorPontuacao.txt");
            i.melhorPontuacao = Integer.parseInt(fileo.readString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        i.batch = new SpriteBatch();
        i.fundo1 = new Texture("fundo/fundoMenu.png");
        i.logo = new Texture("fundo/logo.png");
        i.bestScore = new Texture("fundo/bestScore.png");
        i.uvaPontosMaximo = new Texture("fundo/uvaPontosMaximo.png");
        i.uvaPontuacaoMaxima = new BitmapFont(Gdx.files.internal("fontes/fonteGold.fnt"), Gdx.files.internal("fontes/fonteGold.png"), false);
        i.clique = Gdx.audio.newSound(Gdx.files.internal("sons/clique.wav"));
        i.musicaMenu = Gdx.audio.newMusic(Gdx.files.internal("sons/musicaMenu.mp3"));
        i.musicaMenu.setLooping(true);
        i.musicaMenu.setVolume(0.7f);

        i.larguraDispositivo = Gdx.graphics.getWidth();
        i.alturaDispositivo = Gdx.graphics.getHeight();
        i.uvaPontuacaoMaxima.getData().setScale(Float.parseFloat(valueOf(i.alturaDispositivo / 600)));
        Gdx.input.setInputProcessor(i.stage);
    }

    @Override
    public void hide()
    {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose()
    {
        i.stage.dispose();
        jogo.dispose();
        i.clique.dispose();
        i.musicaMenu.dispose();
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        i.musicaMenu.play();

        i.batch.begin();
        i.batch.draw(i.fundo1, 0, 0, i.larguraDispositivo * 2.5f, i.alturaDispositivo);
        i.batch.draw( i.logo, i.larguraDispositivo / 6, i.alturaDispositivo / 1.6f, i.larguraDispositivo / 1.5f, i.alturaDispositivo / 5);
        i.batch.draw( i.bestScore, i.larguraDispositivo / 1000, i.alturaDispositivo / 1.47f, i.larguraDispositivo / 12, i.alturaDispositivo / 10);
        i.uvaPontuacaoMaxima.draw(i.batch,valueOf(i.melhorPontuacao), i.larguraDispositivo / 12.5f, i.alturaDispositivo / 1.15f);
        i.batch.draw(i.uvaPontosMaximo, i.alturaDispositivo / 70, i.larguraDispositivo / 2.29f, i.larguraDispositivo / 15, i.alturaDispositivo / 10 );
        i.batch.end();

        i.stage.act();
        i.stage.draw();
    }
}
