#define _LED_GREEN 32
#define _LED_YELLOW 33
#define _LED_RED 27

void setup() {
  Serial.begin(9600);
  // put your setup code here, to run once:
  pinMode(_LED_GREEN, OUTPUT);
  pinMode(_LED_YELLOW, OUTPUT);
  pinMode(_LED_RED, OUTPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  turnGreen();
  turnYellow();
  turnRed();
}

void turnGreen(){
  digitalWrite(_LED_GREEN, HIGH);
  digitalWrite(_LED_YELLOW, LOW);
  digitalWrite(_LED_RED, LOW);
  delay(3000);
}

void turnYellow(){
  digitalWrite(_LED_GREEN, LOW);
  digitalWrite(_LED_YELLOW, HIGH);
  digitalWrite(_LED_RED, LOW);
  delay(1000);
}

void turnRed(){
  digitalWrite(_LED_GREEN, LOW);
  digitalWrite(_LED_YELLOW, LOW);
  digitalWrite(_LED_RED, HIGH);
  delay(2000);
}
