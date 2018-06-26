from kivy.app import App
from kivy.uix.widget import Widget
from kivy.properties import NumericProperty,ObjectProperty
from kivy.clock import Clock
import keyboard

"""
in questo modo si crea una applicazione (estendendo App) che non fa altro che ritornare una istanza
del pong (che estende widget)

importante->il metodo build di ogni App deve ritornare un Widget

il file pong.kv è sostanzialmente come il metodo paintComponent, serve a disegnare la grafica aggiuntiva (tipo la sbarra centrale
e i segna punti) e tutti gli elementi

importante->il file .kv deve avere lo stesso nome della classe che estende App (es. PongApp->pong)
"""
class Ball(Widget):
	x_speed=NumericProperty(8)
	y_speed=NumericProperty(4)

	def move(self):
		self.x+=self.x_speed
		self.y+=self.y_speed

	def reset_ball(self,center,reset_factor): #la pallina parte dalla parte opposta rispetto a chi ha fatto punto
		self.x_speed=8*reset_factor
		self.y_speed=4*reset_factor
		self.center=center

class Player(Widget):
	score=NumericProperty(0)

	def ball_bounce(self,ball):
		if(self.collide_widget(ball)):
			ball.x_speed*=-1



class PongGame(Widget):
	ball=ObjectProperty(None)
	player1=ObjectProperty(None)
	player2=ObjectProperty(None)



	def update(self,dt):
		self.ball.move()
		self.ball.x_speed+=self.ball.x_speed*0.0005
		self.ball.y_speed+=self.ball.y_speed*0.0005
		self.player1.ball_bounce(self.ball)
		self.player2.ball_bounce(self.ball)
		self.key_pressed()

		if(self.ball.x<0):
			self.player1.score+=1
			self.ball.reset_ball(self.center,-1)
		if(self.ball.x>self.width):
			self.player2.score+=1
			self.ball.reset_ball(self.center,1)
		if(self.ball.y<0) or (self.ball.y>self.height-self.ball.height):
			self.ball.y_speed*=-1

	def key_pressed(self):
		if(keyboard.is_pressed('w')):
			if(self.player1.y<self.height-self.player1.height):
				self.player1.center_y+=12
		if(keyboard.is_pressed('s')):
			if(self.player1.y>0):
				self.player1.center_y-=12
		if(keyboard.is_pressed('i')):
			if(self.player2.y<self.height-self.player2.height):
				self.player2.center_y+=12
		if(keyboard.is_pressed('k')):
			if(self.player2.y>0):
				self.player2.center_y-=12
		
class PongApp(App):

	def build(self):
		game=PongGame()
		game.ball.reset_ball(game.center,1)
		Clock.schedule_interval(game.update,1.0/60.0)
		return game

if __name__ == '__main__':
	app=PongApp()
	app.run()