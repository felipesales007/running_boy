package com.felipesales007.chamadas;

import com.badlogic.gdx.Game;
import com.felipesales007.jogo.Intro;
import com.felipesales007.jogo.Jogo;
import com.felipesales007.jogo.Menu;

public class Telas extends Game
{
    public Intro introJogo;
    public Menu menuJogo;
    public Jogo playJogo;

    @Override
    public void create ()
    {
        introJogo = new Intro(this);
        menuJogo = new Menu(this);
        playJogo = new Jogo(this);

        setScreen(introJogo);
    }
}
