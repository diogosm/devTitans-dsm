#define _LED_YELLOW 33
#define prescaler 40
#define contador (40000000/prescaler)
#define contagem_em_segundos 2
#define _LDR 2

volatile bool interrupted = false;
int contagem_interrupcao = 0;
bool LED = false;

hw_timer_t * timer = NULL;

void IRAM_ATTR onTimer() {
  contagem_interrupcao++;
  if (contagem_interrupcao == contagem_em_segundos)
    interrupted = true;
}

void setup() {
  Serial.begin(115200);
  pinMode(_LED_YELLOW, OUTPUT);
  digitalWrite(_LED_YELLOW, HIGH);
  timer = timerBegin(0, prescaler, true);
  timerAttachInterrupt(timer, &onTimer, true);
  timerAlarmWrite(timer, contador, true);
  timerAlarmEnable(timer);
}

void loop() {
  if (interrupted) {
    contagem_interrupcao = 0;
    Serial.print("LDR: ");
    Serial.println(analogRead(_LDR));
    interrupted = false;
    digitalWrite(_LED_YELLOW, LED);
    LED = !LED;
  }
}
