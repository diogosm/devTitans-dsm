#define _LED_GREEN 32
#define _LED_YELLOW 33
#define _LED_RED 27
#define _LED_SP_RED 18
#define _LED_SP_GREEN 5
#define _BUT 23

const int STATE_0 = 0;
const int STATE_1 = 1;
const int STATE_2 = 2;
const int STATE_3 = 3;

int button_pressed;
bool last;
short int state;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);

  pinMode(_LED_GREEN, OUTPUT);
  pinMode(_LED_YELLOW, OUTPUT);
  pinMode(_LED_RED, OUTPUT);
  pinMode(_LED_SP_RED, OUTPUT);
  pinMode(_LED_SP_GREEN, OUTPUT);

  pinMode(_BUT, INPUT_PULLUP);
  last = digitalRead(_BUT);
  state = STATE_0;

  //semaforo de carros
  digitalWrite(_LED_GREEN, HIGH);
  digitalWrite(_LED_YELLOW, LOW);
  digitalWrite(_LED_RED, LOW);
  //semaforo de pedestres
  digitalWrite(_LED_SP_GREEN, LOW);
  digitalWrite(_LED_SP_RED, HIGH);
}

void loop() {
  // put your main code here, to run repeatedly:
  bool now;

  switch(state){

    case STATE_0:
      now = digitalRead(_BUT);
      if(now != last){
        Serial.print("Tuts ... ");
        Serial.print(now);
        Serial.print(" ");
        Serial.println(last);
        state = STATE_1;
        //last = now;
        delay(100);
      }else{

      }
      break;
    
    case STATE_1:
      do_state1();
      state = STATE_2;
      break;

    case STATE_2:
      do_state2();
      state = STATE_3;
      break;

    case STATE_3:
      do_state3();
      state = STATE_0;
      delay(100);
      break;

  }
}

void do_state1(){
  Serial.println("CARROS = verde e PEDESTRES = vermelho;");

  digitalWrite(_LED_GREEN, LOW);
  digitalWrite(_LED_YELLOW, HIGH);
  digitalWrite(_LED_RED, LOW);
  digitalWrite(_LED_SP_GREEN, LOW);
  digitalWrite(_LED_SP_RED, HIGH);
  delay(2000);
}

void do_state2(){
  Serial.println("CARROS = vermelho e PEDESTRES = verde;");

  digitalWrite(_LED_GREEN, LOW);
  digitalWrite(_LED_YELLOW, LOW);
  digitalWrite(_LED_RED, HIGH);
  digitalWrite(_LED_SP_GREEN, HIGH);
  digitalWrite(_LED_SP_RED, LOW);
  delay(3000);
}

void do_state3(){
  Serial.println("CARROS = amarelo. ");

  for(int i = 0;i<5;i++){
    digitalWrite(_LED_SP_GREEN, HIGH);
    delay(100);
    digitalWrite(_LED_SP_GREEN, LOW);
    delay(100);
  }

  //transita de volta pro 0
  digitalWrite(_LED_GREEN, HIGH);
  digitalWrite(_LED_YELLOW, LOW);
  digitalWrite(_LED_RED, LOW);
  digitalWrite(_LED_SP_GREEN, LOW);
  digitalWrite(_LED_SP_RED, HIGH);
}
