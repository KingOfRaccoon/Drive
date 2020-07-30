package com.mygdx.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.screens.GameScreen
// Класс игры
class MyGame: Game() {
    lateinit var batch: SpriteBatch // отрисовчик
    lateinit var joy_back: Texture // текстура для фона джойстика
    lateinit var joy_stick : Texture // текстура для стика джойстика
    lateinit var actor: Texture // текстура для персонажа

    override fun create() { // вызывается при запуске, здесь идёт загрузка ресурсов
        batch = SpriteBatch() // иницифлизация отрисовчика
        joy_back = Texture("jo_back.png") // загрузка текстуры фона джойстика
        joy_stick = Texture("jo_face.png") // загрузка текстуры для стика джойстика
        actor = Texture("persUp.png") // загрузка текстуры для персонажа
        setScreen(GameScreen(this)) // установка в отрисовку экрана меню
    }

    override fun dispose() {//выгрузка ресурсов
        batch.dispose() // выгрузка из памяти отрисовчика
        joy_stick.dispose() // выгрузка текстуры фона джойстика
        joy_back.dispose() // выгрузка текстуры стика джойстика
        actor.dispose() // выгрузка
    }
}