unsigned char distancia[14];
const unsigned long baund_rate = 9600;
sbit LCD_RS at RC0_bit;
sbit LCD_EN at RC1_bit;
sbit LCD_D7 at RB7_bit;
sbit LCD_D6 at RB6_bit;
sbit LCD_D5 at RB5_bit;
sbit LCD_D4 at RB4_bit;

sbit LCD_RS_Direction at TRISC0_bit;
sbit LCD_EN_Direction at TRISC1_bit;
sbit LCD_D7_Direction at TRISB7_bit;
sbit LCD_D6_Direction at TRISB6_bit;
sbit LCD_D5_Direction at TRISB5_bit;
sbit LCD_D4_Direction at TRISB4_bit;

void interrupt() //rutina de servicio de interrupciones (MikroC)
{
     if ((PIR1.RCIF == 1) && (UART1_Data_Ready() == 1))
     {
       if (UART1_Data_Ready() == 1) {
          UART1_Read_Text(distancia, "EOL", 255);
          Lcd_out(1,1, distancia);
       }
     }
 PIR1.RCIF = 0; // se borra el flag de la interrupción RCIE
}

void main(){
Lcd_Init();
UART1_Init(baund_rate);

//RECEIVE USART CONFIGURATION
RCSTA.SREN = 1;
RCON.IPEN = 0; //se deshabilitan las interrupciones con prioridad (MikroC)
PIR1.RCIF = 0; //se pone a cero el flag de la interrupción RCIE
PIE1.RCIE = 1; // se habilita la interrupción RCIE
INTCON.PEIE = 1; //es de tipo peripheral
INTCON.GIE = 1; // se habilitan las interrupciones en general

while(1);
}