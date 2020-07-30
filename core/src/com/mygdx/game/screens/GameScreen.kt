package com.mygdx.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.MyGame
import com.mygdx.game.actors.Player
import com.mygdx.game.tools.Joystick
import com.mygdx.game.tools.Point2D

class GameScreen(var game: MyGame): Screen, InputProcessor { // Экран для отрисовки меню
    lateinit var joystick: Joystick
    lateinit var player: Player

    override fun hide() {// вызывается, когда экран заменяется другим или приложение закрыватеся
        dispose()
    }

    override fun show() {// вызывается, когда экран начинает отображаться на экране телефона
        Gdx.input.inputProcessor = this // установка обработчика нажатий
        loadActors()
        loadInterface()
    }

    override fun render(delta: Float) {// здесь происходит покадровая отрисовка всего, что находится на экране
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT) // закрашивание прошлого кадра
        gameUpdate()
        game.batch.begin()
        gameRender(game.batch)
        game.batch.end()
    }

    override fun pause() { // пауза
    }

    override fun resume() { // продолжение, тут всё ясно
    }

    override fun resize(width: Int, height: Int) {// здесь можно изменять размер camera или viewport

    }

    override fun dispose() { // выгрузка текстур из памяти
        joystick.textureStick.dispose()
        joystick.textureCircle.dispose()
    }

    fun loadActors(){ // определение игроков на экране
        player = Player(game.actor, Point2D(Gdx.graphics.width/2.toFloat(), Gdx.graphics.height/2.toFloat()), 20f).apply { speed = 10f }
    }

    fun loadInterface(){ // определение интерфейса для управления
        joystick = Joystick(game.joy_back, game.joy_stick, Gdx.graphics.height/3.toFloat())
    }

    fun multiTouch(x: Float, y: Float, isDownTouch: Boolean, pointer: Int){ // обработчик нажатий для интерфейса
        joystick.update(x, y, isDownTouch, pointer)
    }

    fun gameUpdate(){ // обновление местоположения и состояния объектов на экране
        player.move(joystick.direction)
        player.update()
    }

    fun gameRender(batch: SpriteBatch){ // отрисовка всех объектов на экране
        player.draw(batch)
        joystick.draw(batch)
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {// вызывается при нажатии на экран
        multiTouch(screenX.toFloat(), (Gdx.graphics.height - screenY).toFloat(), false, pointer)
        return false
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean { // вызывается при перемещении пальца по экрану
        multiTouch(screenX.toFloat(), (Gdx.graphics.height - screenY).toFloat(), true, pointer)
        return false
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean { // вызывается, когда палец перестаёт касаеться экрана
        multiTouch(screenX.toFloat(), (Gdx.graphics.height - screenY).toFloat(), true, pointer)
        return false
    }

    override fun keyUp(keycode: Int): Boolean { // это для клавиатуры или для кнопок меню и назад на телефоне
        return false
    }

    override fun keyDown(keycode: Int): Boolean { // это для клавиатуры или для кнопок меню и назад на телефоне
        return false
    }

    override fun keyTyped(character: Char): Boolean { // нужен для реализации текстовых полей
        return false
    }

    // на телефонах эти методы не используются
    override fun scrolled(amount: Int): Boolean { // вызывается при вращении колёсика мыши
        TODO("Not yet implemented")
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean { // это для использования мышки
        TODO("Not yet implemented")
    }
}
