package com.felipesales007.jogo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.felipesales007.chamadas.BaseTela;
import com.felipesales007.chamadas.Importacao;
import com.felipesales007.chamadas.Telas;

import java.util.Random;

import static com.felipesales007.jogo.Jogo.State.RUN;
import static java.lang.String.valueOf;

public class Jogo extends BaseTela
{
    Importacao i = new Importacao();

    public Jogo(final Telas jogo)
    {
        super(jogo);
        // BOTÃO PAUSE E PLAY DO JOGO
        i.larguraDispositivo = Gdx.graphics.getWidth();
        i.alturaDispositivo = Gdx.graphics.getHeight();

        i.stagePause = new Stage(new FitViewport(i.larguraDispositivo / 2, i.alturaDispositivo / 2));
        i.skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        i.pause = new TextButton("||", i.skin);

        i.pause.getLabel().setFontScale(i.larguraDispositivo / 900, i.alturaDispositivo / 900);
        i.pause.setSize(i.larguraDispositivo / 25, i.alturaDispositivo / 25);
        i.pause.setPosition(i.larguraDispositivo / 54, i.alturaDispositivo / 2.525f);
        i.pause.setColor(Color.GOLD);
        i.stagePause.addActor(i.pause);
    }

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
        i.numeroRandomico = new Random();
        i.flechaRandomica = new Random();
        i.uvaPontuacao = new BitmapFont(Gdx.files.internal("fontes/fontePrincipal.fnt"), Gdx.files.internal("fontes/fontePrincipal.png"), false);
        i.uvaPontuacaoMaxima = new BitmapFont(Gdx.files.internal("fontes/fonteGold.fnt"), Gdx.files.internal("fontes/fonteGold.png"), false);

        i.boyRetangulo = new Rectangle();
        i.caquitoRetangulo = new Rectangle();
        i.buracoFrenteRetangulo = new Rectangle();
        i.buracoMeioRetangulo = new Rectangle();
        i.buracoFundoRetangulo = new Rectangle();
        i.flechaRetangulo = new Rectangle();
        i.uvaRetangulo = new Rectangle();
        i.shape = new ShapeRenderer();

        i.fundo1 = new Texture("fundo/fundo.png");
        i.fundo2 = new Texture("fundo/fundo.png");
        i.uvaPontos = new Texture("fundo/uvaPontos.png");
        i.uvaPontosMaximo = new Texture("fundo/uvaPontosMaximo.png");
        i.bestScore = new Texture("fundo/bestScore.png");
        i.newRecord = new Texture("fundo/newRecord.png");
        i.placa = new Texture("fundo/placa.png");

        i.boyParado = new Texture[2];
        i.boyParado[0] = new Texture("correndo/boyCorrendo3.png");
        i.boyParado[1] = new Texture("correndo/boyCorrendo4.png");

        i.boyCorrendo = new Texture[8];
        i.boyCorrendo[0] = new Texture("correndo/boyCorrendo1.png");
        i.boyCorrendo[1] = new Texture("correndo/boyCorrendo2.png");
        i.boyCorrendo[2] = new Texture("correndo/boyCorrendo3.png");
        i.boyCorrendo[3] = new Texture("correndo/boyCorrendo4.png");
        i.boyCorrendo[4] = new Texture("correndo/boyCorrendo5.png");
        i.boyCorrendo[5] = new Texture("correndo/boyCorrendo6.png");
        i.boyCorrendo[6] = new Texture("correndo/boyCorrendo7.png");
        i.boyCorrendo[7] = new Texture("correndo/boyCorrendo8.png");

        i.boyPulando = new Texture[8];
        i.boyPulando[0] = new Texture("pulando/boyPulando2.png");
        i.boyPulando[1] = new Texture("pulando/boyPulando3.png");
        i.boyPulando[2] = new Texture("pulando/boyPulando4.png");
        i.boyPulando[3] = new Texture("pulando/boyPulando5.png");
        i.boyPulando[4] = new Texture("pulando/boyPulando6.png");
        i.boyPulando[5] = new Texture("pulando/boyPulando7.png");
        i.boyPulando[6] = new Texture("pulando/boyPulando8.png");
        i.boyPulando[7] = new Texture("pulando/boyPulando9.png");

        i.boyBatida = new Texture[2];
        i.boyBatida[0] = new Texture("batida/boyBatida1.png");
        i.boyBatida[1] = new Texture("batida/boyBatida2.png");

        i.boyQueda = new Texture[2];
        i.boyQueda[0] = new Texture("queda/boyQueda1.png");
        i.boyQueda[1] = new Texture("queda/boyQueda2.png");

        i.gameOver = new Texture[18];
        i.gameOver[0] = new Texture("gameOver/1.png");
        i.gameOver[1] = new Texture("gameOver/2.png");
        i.gameOver[2] = new Texture("gameOver/3.png");
        i.gameOver[3] = new Texture("gameOver/4.png");
        i.gameOver[4] = new Texture("gameOver/5.png");
        i.gameOver[5] = new Texture("gameOver/6.png");
        i.gameOver[6] = new Texture("gameOver/7.png");
        i.gameOver[7] = new Texture("gameOver/8.png");
        i.gameOver[8] = new Texture("gameOver/9.png");
        i.gameOver[9] = new Texture("gameOver/10.png");
        i.gameOver[10] = new Texture("gameOver/11.png");
        i.gameOver[11] = new Texture("gameOver/12.png");
        i.gameOver[12] = new Texture("gameOver/13.png");
        i.gameOver[13] = new Texture("gameOver/14.png");
        i.gameOver[14] = new Texture("gameOver/15.png");
        i.gameOver[15] = new Texture("gameOver/16.png");
        i.gameOver[16] = new Texture("gameOver/17.png");
        i.gameOver[17] = new Texture("gameOver/18.png");

        i.caquito = new Texture("obstaculo/caquito.png");
        i.flecha = new Texture("obstaculo/flecha.png");
        i.buraco = new Texture("obstaculo/buraco.png");
        i.uva = new Texture("obstaculo/uva.png");

        i.musica = Gdx.audio.newMusic(Gdx.files.internal("sons/musica.wav"));
        i.musica.setLooping(true);
        i.go = Gdx.audio.newSound(Gdx.files.internal("sons/go.wav"));
        i.pulo = Gdx.audio.newSound(Gdx.files.internal("sons/pulo.wav"));
        i.au = Gdx.audio.newSound(Gdx.files.internal("sons/au.wav"));
        i.quedaBuraco = Gdx.audio.newSound(Gdx.files.internal("sons/queda.wav"));
        i.hit = Gdx.audio.newSound(Gdx.files.internal("sons/hit.wav"));
        i.ponto = Gdx.audio.newSound(Gdx.files.internal("sons/ponto.wav"));
        i.pontoRecord = Gdx.audio.newSound(Gdx.files.internal("sons/pontoRecord.wav"));
        i.perigo = Gdx.audio.newSound(Gdx.files.internal("sons/perigo.wav"));
        i.clique = Gdx.audio.newSound(Gdx.files.internal("sons/clique.wav"));
        i.record = Gdx.audio.newSound(Gdx.files.internal("sons/newRecordSom.mp3"));

        Gdx.input.setInputProcessor(i.stagePause);
        i.larguraDispositivo = Gdx.graphics.getWidth();
        i.alturaDispositivo = Gdx.graphics.getHeight();
        i.uvaPontuacao.getData().setScale(Float.parseFloat(valueOf(i.alturaDispositivo / 600)));
        i.uvaPontuacaoMaxima.getData().setScale(Float.parseFloat(valueOf(i.alturaDispositivo / 600)));
        i.boyPosicao = i.alturaDispositivo / 7.3f;
        i.movimentoFundo1 = 0;
        i.movimentoFundo2 = i.larguraDispositivo;
        i.movimentoCaquito = i.larguraDispositivo;
        i.movimentoFlecha = i.larguraDispositivo;
        i.movimentoBuraco = i.larguraDispositivo;
        i.movimentoUva = i.larguraDispositivo + i.larguraDispositivo / 3.7f;
        i.movimentoUvaDelay = i.larguraDispositivo;
    }

    public void render(final float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        switch (i.estado)
        {
            case RUN:
                estadoJogo();
                animacao();
                i.tempoBtn++;
                if (i.tempoBtn > 15 && i.pause.getClickListener().isPressed())
                {
                    i.clique.play();
                    i.pause.getClickListener().cancel();
                    i.pause.getLabel().setFontScale(i.larguraDispositivo / 600, i.alturaDispositivo / 450);
                    i.pause.setText(">");
                    i.estado = State.PAUSE;
                }
                break;
            case PAUSE:
                i.musica.pause();
                animacao();
                if (i.pause.getClickListener().isPressed() || i.estadoJogo == 3 || i.estadoJogo == 4)
                {
                    i.clique.play();
                    i.pause.getClickListener().cancel();
                    i.pause.getLabel().setFontScale(i.larguraDispositivo / 900, i.alturaDispositivo / 900);
                    i.pause.setText("||");
                    i.estado = RUN;
                }
                break;
        }

        if (i.estadoJogo != 3 && i.estadoJogo != 4)
        {
            i.stagePause.act();
            i.stagePause.draw();
        }
    }

    @Override
    public void dispose()
    {
        super.dispose();
        i.batch.dispose();
        i.fundo1.dispose();
        i.fundo2.dispose();
        i.uvaPontos.dispose();
        i.uvaPontosMaximo.dispose();
        i.caquito.dispose();
        i.flecha.dispose();
        i.buraco.dispose();
        i.uva.dispose();
        i.stage.dispose();
        i.stagePause.dispose();
        i.skin.dispose();
        i.introNome.dispose();
        i.uvaPontuacao.dispose();
        i.uvaPontosMaximo.dispose();
        i.introSom.dispose();
        i.musica.dispose();
        i.go.dispose();
        i.pulo.dispose();
        i.au.dispose();
        i.quedaBuraco.dispose();
        i.hit.dispose();
        i.ponto.dispose();
        i.pontoRecord.dispose();
        i.perigo.dispose();
        i.clique.dispose();
        i.shape.dispose();
        jogo.dispose();
    }

    ////////////////////////////////////// IMAGENS DO JOGO /////////////////////////////////////////
    private void animacao()
    {
        i.batch.begin(); // INICIO DO JOGO
        // FUNDO
        i.batch.draw(i.fundo1, i.movimentoFundo1, 0, i.larguraDispositivo * 2.5f, i.alturaDispositivo);
        i.batch.draw(i.fundo2, i.movimentoFundo2, 0, i.larguraDispositivo * 2.5f, i.alturaDispositivo);
        i.batch.draw(i.caquito, i.movimentoCaquito, i.alturaDispositivo / 7.6f, i.larguraDispositivo / 10, i.alturaDispositivo / 6.5f);
        i.batch.draw(i.buraco, i.movimentoBuraco, i.alturaDispositivo / 1000, i.larguraDispositivo / 4.5f, i.alturaDispositivo / 7.7f);
        i.batch.draw(i.flecha, i.movimentoFlecha, i.alturaDispositivo / 8.5f + i.alturaFlecha, i.larguraDispositivo / 4, i.alturaDispositivo / 6);

        // PARADO
        if (i.estadoJogo == 0)
        {
            i.batch.draw(i.boyParado[(int) i.variacaoParado], i.larguraDispositivo / 14, i.boyPosicao, i.larguraDispositivo/ 7, i.alturaDispositivo / 5);
        }
        // CORRENDO
        if (i.boyPosicao <= i.alturaDispositivo / 6.96f && i.estadoJogo == 1 && i.estadoJogo != 3)
        {
            i.uvaPontuacao.draw(i.batch, valueOf(i.pontuacao), i.alturaDispositivo / 0.663f, i.alturaDispositivo / 1.147f);
            i.batch.draw(i.uvaPontos, i.alturaDispositivo / 0.724f, i.larguraDispositivo / 2.29f, i.larguraDispositivo / 15, i.alturaDispositivo / 10);
            i.batch.draw(i.boyCorrendo[(int) i.variacaoCorrendo], i.larguraDispositivo / 14, i.boyPosicao, i.larguraDispositivo / 7, i.alturaDispositivo / 5);
        }
        // PULANDO
        if (i.boyPosicao >= i.alturaDispositivo / 6.9f && i.estadoJogo != 3)
        {
            i.uvaPontuacao.draw(i.batch, valueOf(i.pontuacao), i.alturaDispositivo / 0.663f, i.alturaDispositivo / 1.147f);
            i.batch.draw(i.uvaPontos, i.alturaDispositivo / 0.724f, i.larguraDispositivo / 2.29f, i.larguraDispositivo / 15, i.alturaDispositivo / 10);
            i.batch.draw(i.boyPulando[(int) i.variacaoPulo], i.larguraDispositivo / 14, i.boyPosicao, i.larguraDispositivo / 7, i.alturaDispositivo / 5);
        }
        // GAME OVER (NOVO RECORD)
        if (i.newPonto == 1)
        {
            i.batch.draw(i.placa, i.larguraDispositivo / 1.55f, i.alturaDispositivo / 7.5f, i.larguraDispositivo / 3.1f, i.alturaDispositivo / 3);
            i.batch.draw(i.newRecord, i.larguraDispositivo / 1.465f, i.alturaDispositivo / 3.3f, i.larguraDispositivo /4, i.alturaDispositivo / 6);
            i.uvaPontuacaoMaxima.draw(i.batch,valueOf(i.melhorPontuacao), i.larguraDispositivo / 1.29f, i.alturaDispositivo / 3.1f);
        }
        // COLISÃO
        if (i.estadoJogo == 3)
        {
            if (i.newPonto == 0)
            {
                i.batch.draw( i.bestScore, i.larguraDispositivo / 1000, i.alturaDispositivo / 1.47f, i.larguraDispositivo / 12, i.alturaDispositivo / 10);
                i.uvaPontuacaoMaxima.draw(i.batch,valueOf(i.melhorPontuacao), i.larguraDispositivo / 12.5f, i.alturaDispositivo / 1.15f);
                i.batch.draw(i.uvaPontosMaximo, i.alturaDispositivo / 70, i.larguraDispositivo / 2.29f, i.larguraDispositivo / 15, i.alturaDispositivo / 10 );
                i.uvaPontuacao.draw(i.batch, valueOf(i.pontuacao), i.alturaDispositivo / 0.663f, i.alturaDispositivo / 1.147f);
                i.batch.draw(i.uvaPontos, i.alturaDispositivo / 0.724f, i.larguraDispositivo / 2.29f, i.larguraDispositivo / 15, i.alturaDispositivo / 10);
            }
            i.batch.draw(i.gameOver[(int) i.variacaoGameOver], i.alturaDispositivo / 1.5f, i.larguraDispositivo / 4, i.larguraDispositivo / 4, i.alturaDispositivo / 4);
            i.batch.draw(i.boyBatida[(int) i.variacaoBatida], i.larguraDispositivo / 14, i.boyPosicao, i.larguraDispositivo / 7, i.alturaDispositivo / 5);
        }
        // QUEDA
        if (i.estadoJogo == 4)
        {
            if (i.newPonto == 0)
            {
                i.batch.draw( i.bestScore, i.larguraDispositivo / 1000, i.alturaDispositivo / 1.47f, i.larguraDispositivo / 12, i.alturaDispositivo / 10);
                i.uvaPontuacaoMaxima.draw(i.batch,valueOf(i.melhorPontuacao), i.larguraDispositivo / 12.5f, i.alturaDispositivo / 1.15f);
                i.batch.draw(i.uvaPontosMaximo, i.alturaDispositivo / 70, i.larguraDispositivo / 2.29f, i.larguraDispositivo / 15, i.alturaDispositivo / 10 );
                i.uvaPontuacao.draw(i.batch, valueOf(i.pontuacao), i.alturaDispositivo / 0.663f, i.alturaDispositivo / 1.147f);
                i.batch.draw(i.uvaPontos, i.alturaDispositivo / 0.724f, i.larguraDispositivo / 2.29f, i.larguraDispositivo / 15, i.alturaDispositivo / 10);
            }
            i.batch.draw(i.gameOver[(int) i.variacaoGameOver], i.alturaDispositivo / 1.5f, i.larguraDispositivo / 4, i.larguraDispositivo / 4, i.alturaDispositivo / 4);
            if (i.caiu == 1)
            {
                i.batch.draw(i.boyQueda[(int) i.variacaoQueda], i.larguraDispositivo / 14, i.boyPosicao, i.larguraDispositivo / 7, i.alturaDispositivo / 5);
                i.boyPosicao = i.boyPosicao - i.alturaDispositivo / 85;
            }

            if (i.caiu == 2)
            {
                i.batch.draw(i.boyQueda[(int) i.variacaoQueda], i.larguraDispositivo / 14, i.boyPosicao, i.larguraDispositivo / 7, i.alturaDispositivo / 5);
                i.boyPosicao = i.boyPosicao - i.alturaDispositivo / 85;
            }

            if (i.caiu == 3)
            {
                i.batch.draw(i.boyQueda[(int) i.variacaoQueda], i.queda, i.boyPosicao, i.larguraDispositivo / 7, i.alturaDispositivo / 5);
                i.queda = i.queda - i.alturaDispositivo / 120;
                i.boyPosicao = i.boyPosicao - i.alturaDispositivo / 85;
            }
        }
        // MARCANDO PONTO
        if (i.uvaAtiva == 1)
        {
            i.marcouPonto = false;
            // CAQUITO
            if (i.obstaculoRandomico < 2)
            {
                if (i.obstaculoExtraRandomico == 1)
                {
                    if (i.movimentoCaquito < i.larguraDispositivo / 1.4f)
                    {
                        i.batch.draw(i.uva, i.movimentoUva, i.alturaDispositivo / 8.5f, i.larguraDispositivo / 13, i.alturaDispositivo / 13);
                    }

                    if (i.movimentoCaquito < i.larguraDispositivo && i.movimentoUva > i.larguraDispositivo / 3)
                    {
                        i.batch.draw(i.uva, i.movimentoUva, i.alturaDispositivo / 8.5f, i.larguraDispositivo / 13, i.alturaDispositivo / 13);
                    }

                    if (i.movimentoBuraco < i.larguraDispositivo / 15)
                    {
                        i.movimentoUva = i.larguraDispositivo + i.larguraDispositivo / 3.7f;
                    }
                }
                else
                {
                    if (i.movimentoCaquito < i.larguraDispositivo / 1.4f)
                    {
                        i.batch.draw(i.uva, i.movimentoUva, i.alturaDispositivo / 8.5f, i.larguraDispositivo / 13, i.alturaDispositivo / 13);
                    }

                    if (i.movimentoCaquito < i.larguraDispositivo && i.movimentoUva > i.larguraDispositivo / 3)
                    {
                        i.batch.draw(i.uva, i.movimentoUva, i.alturaDispositivo / 8.5f, i.larguraDispositivo / 13, i.alturaDispositivo / 13);
                    }
                }
            }
            // BURACO
            if (i.obstaculoRandomico == 2)
            {
                if (i.obstaculoExtraRandomico == 1)
                {
                    if (i.movimentoBuraco < i.larguraDispositivo / 1.4f)
                    {
                        i.batch.draw(i.uva, i.movimentoUva, i.alturaDispositivo / 8.5f, i.larguraDispositivo / 13, i.alturaDispositivo / 13);
                    }

                    if (i.movimentoBuraco < i.larguraDispositivo && i.movimentoUva > i.larguraDispositivo / 3)
                    {
                        i.batch.draw(i.uva, i.movimentoUva, i.alturaDispositivo / 8.5f, i.larguraDispositivo / 13, i.alturaDispositivo / 13);
                    }

                    if (i.movimentoCaquito < i.larguraDispositivo / 15)
                    {
                        i.movimentoUva = i.larguraDispositivo + i.larguraDispositivo / 3.7f;
                    }
                }
                else
                {
                    if (i.movimentoBuraco < i.larguraDispositivo / 1.4f)
                    {
                        i.batch.draw(i.uva, i.movimentoUva, i.alturaDispositivo / 8.5f, i.larguraDispositivo / 13, i.alturaDispositivo / 13);
                    }

                    if (i.movimentoBuraco < i.larguraDispositivo && i.movimentoUva > i.larguraDispositivo / 3)
                    {
                        i.batch.draw(i.uva, i.movimentoUva, i.alturaDispositivo / 8.5f, i.larguraDispositivo / 13, i.alturaDispositivo / 13);
                    }
                }
            }
            // FLECHA
            if (i.obstaculoRandomico == 3)
            {
                i.movimentoUva = i.larguraDispositivo + i.larguraDispositivo / 3.7f;
            }
        }
        i.batch.end(); // FIM DO JOGO
        //objetosColisoes(); //<---- PARA VER OS OBJETOS DE COLISÕES
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// ESTADOS DE AÇÃO DO JOGO /////////////////////////////////////
    private void estadoJogo()
    {
        // ESTADOS: 0 -> PARADO, 1 -> CORRENDO, 2 -> PULANDO, 3 -> COLISÃO, 4 -> QUEDA
        i.deltaTime = Gdx.graphics.getDeltaTime();
        i.musica.play();
        variacoes();
        colisoes();

        if (i.estadoJogo == 0 && !i.pause.getClickListener().isPressed())
        {
            if (Gdx.input.justTouched())
            {
                i.obstaculoRandomico = i.numeroRandomico.nextInt(4);
                i.obstaculoExtraRandomico = i.numeroRandomico.nextInt(2);

                if (i.obstaculoRandomico == 3)
                {
                    i.perigo.play(1);
                }
                else
                {
                    i.go.play(0.5f);
                }

                i.estadoJogo = 1;
                try
                {
                    Thread.sleep(100);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }

        if (i.estadoJogo == 1 || i.estadoJogo == 2)
        {
            movimentoFundo();
            obstaculos();
            uva();

            if (i.estadoJogo == 1 && !i.pause.getClickListener().isPressed())
            {
                pular();
                if (Gdx.input.justTouched() && i.boyPosicao <= i.alturaDispositivo / 6.8f && i.puloAtivo == 1)
                {
                    i.pulo.play(0.5f);
                    i.pular = true;
                }
                i.puloAtivo = 1;
            }
        }

        if (i.estadoJogo == 3 || i.estadoJogo == 4)
        {
            salvarPontuacao();
            if (i.somHit == 0 && i.estadoJogo == 3)
            {
                i.au.play(0.7f);
                i.hit.play(0.5f);
                i.somHit++;
            }

            if (i.somHit == 0 && i.estadoJogo == 4)
            {
                i.pulo.stop();
                i.quedaBuraco.play(0.7f);
                i.somHit++;
            }

            if (i.estadoJogo == 4 && i.boyPosicao > - i.alturaDispositivo / 6)
            {
                movimentoFundo();
                obstaculos();
                uva();
            }

            i.tempo = i.tempo + 1;
            if (i.tempo > 35)
            {
                if (Gdx.input.justTouched())
                {
                    resetarJogo();
                }
            }
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////// FUNÇÃO PULO ///////////////////////////////////////////
    private void pular()
    {
        if (i.pular == true)
        {
            if (i.boyPosicao < i.alturaDispositivo / 2.27f) // <-- LIMITE DO PULO
            {
                i.boyPosicao = i.boyPosicao + i.alturaDispositivo / 75;
                if (i.boyPosicao >= i.alturaDispositivo / 2.27f) // <-- ALTURA DO PULO
                {
                    i.pular = false;
                }
            }
        }

        if (i.pular == false)
        {
            if (i.boyPosicao > i.alturaDispositivo / 7)
            {
                i.boyPosicao = i.boyPosicao - i.alturaDispositivo / 100;
            }

            if (i.boyPosicao <= i.alturaDispositivo / 7.3f)
            {
                i.pular = false;
            }
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// FUNÇÃO VARIAÇÕES ////////////////////////////////////////
    private void variacoes()
    {
        i.variacaoParado += i.deltaTime * 2;
        if (i.variacaoParado > 2)
        {
            i.variacaoParado = 0;
        }

        i.variacaoCorrendo += i.deltaTime * 10;
        if (i.variacaoCorrendo > 8)
        {
            i.variacaoCorrendo = 0;
        }

        i.variacaoPulo += i.deltaTime * 8;
        if (i.variacaoPulo > 8)
        {
            i.variacaoPulo = 0;
        }

        i.variacaoBatida += i.deltaTime * 5;
        if (i.variacaoBatida > 2)
        {
            i.variacaoBatida = 0;
        }

        i.variacaoQueda += i.deltaTime * 5;
        if (i.variacaoQueda > 2)
        {
            i.variacaoQueda = 0;
        }

        i.variacaoGameOver += i.deltaTime * 10;
        if (i.variacaoGameOver > 18)
        {
            i.variacaoGameOver = 0;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// FUNÇÃO MOVIMENTO DO FUNDO ///////////////////////////////////
    private void movimentoFundo()
    {
        if (i.movimentoFundo1 <= - i.larguraDispositivo * 1.492f)
        {
            i.movimentoFundo2 = i.movimentoFundo2 - i.larguraDispositivo / 175;
            if (i.movimentoFundo2 <= - i.larguraDispositivo * 1.492f)
            {
                i.movimentoFundo2 = i.larguraDispositivo;
                i.movimentoFundo1 = - i.larguraDispositivo * 1.492f;
            }
        }
        i.movimentoFundo1 = i.movimentoFundo1 - i.larguraDispositivo / 175;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////// FUNÇÃO OBSTACULOS ////////////////////////////////////////
    private void obstaculos()
    {
        if (i.obstaculoRandomico < 2)
        {
            i.movimentoCaquito = i.movimentoCaquito - i.larguraDispositivo / 175;
            if (i.obstaculoExtraRandomico == 1 && i.movimentoCaquito < i.larguraDispositivo / 1.8f)
            {
                i.movimentoBuraco = i.movimentoBuraco - i.larguraDispositivo / 175;
                if (i.movimentoBuraco < - i.larguraDispositivo / 3)
                {
                    i.movimentoCaquito = i.larguraDispositivo;
                    i.movimentoBuraco = i.larguraDispositivo;
                    i.obstaculoRandomico = i.numeroRandomico.nextInt(4);
                    i.obstaculoExtraRandomico = i.numeroRandomico.nextInt(2);

                    if (i.obstaculoRandomico == 3)
                    {
                        i.perigo.play();
                    }

                    if (i.movimentoUva < - i.larguraDispositivo / 3)
                    {
                        i.movimentoUva = i.larguraDispositivo;
                        i.uvaAtiva = 1;
                    }
                }
            }

            if (i.movimentoCaquito < - i.larguraDispositivo / 3)
            {
                if (i.obstaculoExtraRandomico == 0)
                {
                    i.movimentoCaquito = i.larguraDispositivo;
                    i.obstaculoRandomico = i.numeroRandomico.nextInt(4);
                    i.obstaculoExtraRandomico = i.numeroRandomico.nextInt(2);

                    if (i.obstaculoRandomico == 3)
                    {
                        i.perigo.play();
                    }

                    if (i.movimentoUva < - i.larguraDispositivo / 3)
                    {
                        i.movimentoUva = i.larguraDispositivo;
                        i.uvaAtiva = 1;
                    }
                }
            }
        }

        if (i.obstaculoRandomico == 2)
        {
            i.movimentoBuraco = i.movimentoBuraco - i.larguraDispositivo / 175;
            if (i.obstaculoExtraRandomico == 1 && i.movimentoBuraco < i.larguraDispositivo / 1.8f)
            {
                i.movimentoCaquito = i.movimentoCaquito - i.larguraDispositivo / 175;
                if (i.movimentoCaquito < - i.larguraDispositivo / 3)
                {
                    i.movimentoBuraco = i.larguraDispositivo;
                    i.movimentoCaquito = i.larguraDispositivo;
                    i.obstaculoRandomico = i.numeroRandomico.nextInt(4);
                    i.obstaculoExtraRandomico = i.numeroRandomico.nextInt(2);

                    if (i.obstaculoRandomico == 3)
                    {
                        i.perigo.play();
                    }

                    if (i.movimentoUva < - i.larguraDispositivo / 3)
                    {
                        i.movimentoUva = i.larguraDispositivo ;
                        i.uvaAtiva = 1;
                    }
                }
            }

            if (i.movimentoBuraco < - i.larguraDispositivo / 3)
            {
                if (i.obstaculoExtraRandomico == 0)
                {
                    i.movimentoBuraco = i.larguraDispositivo;
                    i.obstaculoRandomico = i.numeroRandomico.nextInt(4);
                    i.obstaculoExtraRandomico = i.numeroRandomico.nextInt(2);

                    if (i.obstaculoRandomico == 3)
                    {
                        i.perigo.play();
                    }

                    if (i.movimentoUva < - i.larguraDispositivo / 3)
                    {
                        i.movimentoUva = i.larguraDispositivo;
                        i.uvaAtiva = 1;
                    }
                }
            }
        }

        if (i.obstaculoRandomico == 3)
        {
            if (i.estadoFlecha == false)
            {
                i.alturaFlecha = i.flechaRandomica.nextInt((int) (i.alturaDispositivo / 7.6f));
                i.estadoFlecha = true;
            }
            i.movimentoFlecha = i.movimentoFlecha - i.larguraDispositivo / 55;
            if (i.movimentoFlecha < - i.larguraDispositivo / 3)
            {
                i.estadoFlecha = false;
                i.movimentoFlecha = i.larguraDispositivo;
                i.obstaculoRandomico = i.numeroRandomico.nextInt(4);

                if (i.obstaculoRandomico == 3)
                {
                    i.perigo.play();
                }
            }
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////// UVA PARA PONTO /////////////////////////////////////////
    private void uva()
    {
        i.movimentoUva = i.movimentoUva - i.larguraDispositivo / 175;
        i.movimentoUvaDelay = i.movimentoUva - i.larguraDispositivo / 175;

        if (i.movimentoUva < - i.larguraDispositivo / 3)
        {
            i.movimentoUva = i.larguraDispositivo;
            i.movimentoUvaDelay = i.larguraDispositivo;
        }

        if (i.uvaAtiva == 0 && i.movimentoUvaDelay < - i.larguraDispositivo / 3)
        {
            i.movimentoUva = i.larguraDispositivo;
            i.movimentoUvaDelay = i.larguraDispositivo;
            i.uvaAtiva = 1;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////// FUNÇÃO DE COLISÕES ///////////////////////////////////////
    private void colisoes()
    {
        i.boyRetangulo.set((i.boyCorrendo[3].getDepth() + i.alturaDispositivo / 5.3f) + i.queda, (i.boyPosicao + i.boyPosicao / 7) + (i.queda * 30), (i.larguraDispositivo / 16) + (i.queda * 30), i.alturaDispositivo / 6);
        i.caquitoRetangulo.set(i.movimentoCaquito + i.alturaDispositivo / 25, i.alturaDispositivo / 7.5f, i.larguraDispositivo / 14, i.alturaDispositivo / 6.5f);
        i.buracoFrenteRetangulo.set(i.movimentoBuraco + i.larguraDispositivo / 150, i.alturaDispositivo / 150, i.larguraDispositivo / 18, i.alturaDispositivo / 6);
        i.buracoMeioRetangulo.set(i.movimentoBuraco + i.larguraDispositivo / 9, i.alturaDispositivo / 150, i.larguraDispositivo / 70, i.alturaDispositivo / 6);
        i.buracoFundoRetangulo.set(i.movimentoBuraco + i.larguraDispositivo / 5.5f, i.alturaDispositivo / 150, i.larguraDispositivo / 200, i.alturaDispositivo / i.nRetangulo);
        i.flechaRetangulo.set(i.movimentoFlecha + i.alturaDispositivo / 6, i.alturaDispositivo / 5.6f + i.alturaFlecha, i.larguraDispositivo / 8, i.alturaDispositivo / 50);
        i.uvaRetangulo.set(i.movimentoUva, i.alturaDispositivo / 8, i.larguraDispositivo / 15, i.alturaDispositivo / 15.5f);

        if (Intersector.overlaps(i.boyRetangulo, i.caquitoRetangulo))
        {
            i.estadoJogo = 3;
            if (i.vibrarAtivo == 0)
            {
                vibrar();
            }
        }

        if (Intersector.overlaps(i.boyRetangulo, i.flechaRetangulo))
        {
            i.estadoJogo = 3;
            if (i.vibrarAtivo == 0)
            {
                vibrar();
            }
        }

        if (Intersector.overlaps(i.boyRetangulo, i.buracoFrenteRetangulo))
        {
            i.nRetangulo = 300;
            i.estadoJogo = 4;
            i.caiu = 1;
            if (i.vibrarAtivo == 0)
            {
                vibrar();
            }
        }

        if (Intersector.overlaps(i.boyRetangulo, i.buracoMeioRetangulo))
        {
            i.nRetangulo = 300;
            i.estadoJogo = 4;
            i.caiu = 2;
            if (i.vibrarAtivo == 0)
            {
                vibrar();
            }
        }

        if (Intersector.overlaps(i.boyRetangulo, i.buracoFundoRetangulo))
        {
            i.estadoJogo = 4;
            i.caiu = 3;
            if (i.vibrarAtivo == 0)
            {
                vibrar();
            }
        }

        if (Intersector.overlaps(i.boyRetangulo, i.uvaRetangulo))
        {
            if (!i.marcouPonto)
            {
                if (i.pontuacao > i.melhorPontuacao - 1 && i.pontuacaoNovo == 0)
                {
                    i.pontoRecord.play(0.3f);
                    i.pontuacaoNovo = 1;
                }
                else
                {
                    i.ponto.play(0.6f);
                }
                i.pontuacao++;
                i.marcouPonto = true;
            }
            i.uvaAtiva = 0;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// FUNÇÃO PARA VER OBJETOS /////////////////////////////////////
    private void objetosColisoes()
    {
        // USADO SOMENTE PARA VER OS OBJETOS PARA COLISÕES DO JOGO
        i.shape.begin(ShapeRenderer.ShapeType.Filled);
        i.shape.rect(i.boyRetangulo.x, i.boyRetangulo.y, i.boyRetangulo.width, i.boyRetangulo.height);
        i.shape.rect(i.caquitoRetangulo.x, i.caquitoRetangulo.y, i.caquitoRetangulo.width, i.caquitoRetangulo.height);
        i.shape.rect(i.buracoFrenteRetangulo.x, i.buracoFrenteRetangulo.y, i.buracoFrenteRetangulo.width, i.buracoFrenteRetangulo.height);
        i.shape.rect(i.buracoMeioRetangulo.x, i.buracoMeioRetangulo.y, i.buracoMeioRetangulo.width, i.buracoMeioRetangulo.height);
        i.shape.rect(i.buracoFundoRetangulo.x, i.buracoFundoRetangulo.y, i.buracoFundoRetangulo.width, i.buracoFundoRetangulo.height);
        i.shape.rect(i.flechaRetangulo.x, i.flechaRetangulo.y, i.flechaRetangulo.width, i.flechaRetangulo.height);
        i.shape.rect(i.uvaRetangulo.x, i.uvaRetangulo.y, i.uvaRetangulo.width, i.uvaRetangulo.height);
        i.shape.setColor(Color.RED);
        i.shape.end();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////// RESETA O JOGO //////////////////////////////////////////
    private void resetarJogo()
    {
        i.estadoJogo = 0;
        i.boyPosicao = i.alturaDispositivo / 7.3f;
        i.pular = false;
        i.tempo = 0;
        i.tempoBtn = 0;
        i.queda = 0;
        i.newPonto = 0;
        i.puloAtivo = 0;
        i.uvaAtiva = 1;
        i.pontuacao = 0;
        i.pontuacaoNovo = 0;
        i.vibrarAtivo = 0;
        i.somHit = 0;
        i.nRetangulo = 6.4f;
        i.movimentoCaquito = i.larguraDispositivo;
        i.movimentoFlecha = i.larguraDispositivo;
        i.movimentoBuraco = i.larguraDispositivo;
        i.movimentoUva = i.larguraDispositivo + i.larguraDispositivo / 3.7f;
        i.movimentoUvaDelay = i.larguraDispositivo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////// SALVANDO A MELHOR PONTUAÇÃO //////////////////////////////////
    private void salvarPontuacao()
    {
        if (i.pontuacao > i.melhorPontuacao)
        {
            i.record.play();
            i.newPonto = 1;
            i.melhorPontuacao = i.pontuacao;

            FileHandle file = Gdx.files.local("melhorPontuacao.txt");
            file.writeString(valueOf(i.melhorPontuacao), false);
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////// VIBRAR //////////////////////////////////////////////
    private void vibrar()
    {
        i.vibrarAtivo = 1;
        Gdx.input.vibrate(50);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////// ESTADOS PARA O PAUSE //////////////////////////////////////
    public enum State
    {
        RUN,
        PAUSE
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
}