//const float periodo = 1.25e-7; //T=1/8Mhz
const float periodo = 0.5e-6; //Fos/4 & T=1/2Mhz
float t3;
float dis;
unsigned int ciclos;
const unsigned long baund_rate = 9600;
unsigned char distanciaCaracter[14];
void sendTrig(){
  PORTB.B1 = 1;
  Delay_us(10);
  PORTB.B1 = 0;
}

void interrupt(){
     //Interrupt every 1'5 seg
     if(INTCON.TMR0IE && INTCON.TMR0IF){
       TMR0H = (18661>>8);
       TMR0L = 18661;
       sendTrig();
       INTCON.TMR0IF=0;
     }
     //Interrupt every rising edge
     if(INTCON.INT0IE && INTCON.INT0IF && INTCON2.INTEDG0){
         TMR1H=0;
         TMR1L=0;
         T1CON.TMR1ON = 1;
         INTCON.INT0IF = 0;
         INTCON2.INTEDG0 = 0;
     }
     //Interrupt every falling edge
     if(INTCON.INT0IE && INTCON.INT0IF && INTCON2.INTEDG0 == 0){
         ciclos = TMR1L; //LSB
         ciclos = ciclos + (TMR1H<<8); //MSB
         t3 = ciclos * periodo;
         dis = 16570.0 * t3;
         FloatToStr(dis,distanciaCaracter);
         //Change to int
         if(t3 < 0.06){
         //SEND SIGNAL TO OTHER PIC
            if (UART1_Tx_Idle() == 1)
            {
             UART1_Write_Text(distanciaCaracter);
             UART1_Write_Text("EOL");
            }
         }
         INTCON.INT0IF = 0;
         INTCON2.INTEDG0 = 1;
     }
}

void main() {
     TRISB = 0x01;
     TRISC = 0;

     //TIMER0 CONFIGURATION
     T0CON = 0X05;
     TMR0H = (18661>>8);
     TMR0L = 18661;

     //TIMER1 CONFIGURATION
     T1CON = 0X80; //In one 16 bit operation

     INTCON2.RBPU = 1;
     INTCON2.INTEDG0 = 1; //Rising edge interrupt
     INTCON.INT0IF = 0;
     INTCON.INT0IE = 1;

     //TIMER0 INTERRUPTION CONFIGURATION
     INTCON.TMR0IF = 0;
     INTCON.TMR0IE = 1;
     INTCON.GIE = 1;

     //UART MODULE CONFIGURATION AND START
     RCON.IPEN = 0;
     SPBRG = 13;
     TXSTA.SYNC = 0;
     TXSTA.TXEN = 1;
     TXSTA.TRMT = 1;
     TXREG = 0;
     PORTC.B6 = 0;
     UART1_Init(baund_rate);
     
     //START TIMER
     T0CON.TMR0ON = 1;
     while(1);
}