
_sendTrig:

;hae.c,8 :: 		void sendTrig(){
;hae.c,9 :: 		PORTB.B1 = 1;
	BSF         PORTB+0, 1 
;hae.c,10 :: 		Delay_us(10);
	MOVLW       6
	MOVWF       R13, 0
L_sendTrig0:
	DECFSZ      R13, 1, 1
	BRA         L_sendTrig0
	NOP
;hae.c,11 :: 		PORTB.B1 = 0;
	BCF         PORTB+0, 1 
;hae.c,12 :: 		}
L_end_sendTrig:
	RETURN      0
; end of _sendTrig

_interrupt:

;hae.c,14 :: 		void interrupt(){
;hae.c,16 :: 		if(INTCON.TMR0IE && INTCON.TMR0IF){
	BTFSS       INTCON+0, 5 
	GOTO        L_interrupt3
	BTFSS       INTCON+0, 2 
	GOTO        L_interrupt3
L__interrupt16:
;hae.c,17 :: 		TMR0H = (18661>>8);
	MOVLW       72
	MOVWF       TMR0H+0 
;hae.c,18 :: 		TMR0L = 18661;
	MOVLW       229
	MOVWF       TMR0L+0 
;hae.c,19 :: 		sendTrig();
	CALL        _sendTrig+0, 0
;hae.c,20 :: 		INTCON.TMR0IF=0;
	BCF         INTCON+0, 2 
;hae.c,21 :: 		}
L_interrupt3:
;hae.c,23 :: 		if(INTCON.INT0IE && INTCON.INT0IF && INTCON2.INTEDG0){
	BTFSS       INTCON+0, 4 
	GOTO        L_interrupt6
	BTFSS       INTCON+0, 1 
	GOTO        L_interrupt6
	BTFSS       INTCON2+0, 6 
	GOTO        L_interrupt6
L__interrupt15:
;hae.c,24 :: 		TMR1H=0;
	CLRF        TMR1H+0 
;hae.c,25 :: 		TMR1L=0;
	CLRF        TMR1L+0 
;hae.c,26 :: 		T1CON.TMR1ON = 1;
	BSF         T1CON+0, 0 
;hae.c,27 :: 		INTCON.INT0IF = 0;
	BCF         INTCON+0, 1 
;hae.c,28 :: 		INTCON2.INTEDG0 = 0;
	BCF         INTCON2+0, 6 
;hae.c,29 :: 		}
L_interrupt6:
;hae.c,31 :: 		if(INTCON.INT0IE && INTCON.INT0IF && INTCON2.INTEDG0 == 0){
	BTFSS       INTCON+0, 4 
	GOTO        L_interrupt9
	BTFSS       INTCON+0, 1 
	GOTO        L_interrupt9
	BTFSC       INTCON2+0, 6 
	GOTO        L_interrupt9
L__interrupt14:
;hae.c,32 :: 		ciclos = TMR1L; //LSB
	MOVF        TMR1L+0, 0 
	MOVWF       _ciclos+0 
	MOVLW       0
	MOVWF       _ciclos+1 
;hae.c,33 :: 		ciclos = ciclos + (TMR1H<<8); //MSB
	MOVF        TMR1H+0, 0 
	MOVWF       R1 
	CLRF        R0 
	MOVF        _ciclos+0, 0 
	ADDWF       R0, 1 
	MOVF        _ciclos+1, 0 
	ADDWFC      R1, 1 
	MOVF        R0, 0 
	MOVWF       _ciclos+0 
	MOVF        R1, 0 
	MOVWF       _ciclos+1 
;hae.c,34 :: 		t3 = ciclos * periodo;
	CALL        _word2double+0, 0
	MOVLW       189
	MOVWF       R4 
	MOVLW       55
	MOVWF       R5 
	MOVLW       6
	MOVWF       R6 
	MOVLW       106
	MOVWF       R7 
	CALL        _Mul_32x32_FP+0, 0
	MOVF        R0, 0 
	MOVWF       _t3+0 
	MOVF        R1, 0 
	MOVWF       _t3+1 
	MOVF        R2, 0 
	MOVWF       _t3+2 
	MOVF        R3, 0 
	MOVWF       _t3+3 
;hae.c,35 :: 		dis = 16570.0 * t3;
	MOVLW       0
	MOVWF       R4 
	MOVLW       116
	MOVWF       R5 
	MOVLW       1
	MOVWF       R6 
	MOVLW       141
	MOVWF       R7 
	CALL        _Mul_32x32_FP+0, 0
	MOVF        R0, 0 
	MOVWF       _dis+0 
	MOVF        R1, 0 
	MOVWF       _dis+1 
	MOVF        R2, 0 
	MOVWF       _dis+2 
	MOVF        R3, 0 
	MOVWF       _dis+3 
;hae.c,36 :: 		FloatToStr(dis,distanciaCaracter);
	MOVF        R0, 0 
	MOVWF       FARG_FloatToStr_fnum+0 
	MOVF        R1, 0 
	MOVWF       FARG_FloatToStr_fnum+1 
	MOVF        R2, 0 
	MOVWF       FARG_FloatToStr_fnum+2 
	MOVF        R3, 0 
	MOVWF       FARG_FloatToStr_fnum+3 
	MOVLW       _distanciaCaracter+0
	MOVWF       FARG_FloatToStr_str+0 
	MOVLW       hi_addr(_distanciaCaracter+0)
	MOVWF       FARG_FloatToStr_str+1 
	CALL        _FloatToStr+0, 0
;hae.c,38 :: 		if(t3 < 0.06){
	MOVLW       143
	MOVWF       R4 
	MOVLW       194
	MOVWF       R5 
	MOVLW       117
	MOVWF       R6 
	MOVLW       122
	MOVWF       R7 
	MOVF        _t3+0, 0 
	MOVWF       R0 
	MOVF        _t3+1, 0 
	MOVWF       R1 
	MOVF        _t3+2, 0 
	MOVWF       R2 
	MOVF        _t3+3, 0 
	MOVWF       R3 
	CALL        _Compare_Double+0, 0
	MOVLW       1
	BTFSC       STATUS+0, 0 
	MOVLW       0
	MOVWF       R0 
	MOVF        R0, 1 
	BTFSC       STATUS+0, 2 
	GOTO        L_interrupt10
;hae.c,40 :: 		if (UART1_Tx_Idle() == 1)
	CALL        _UART1_Tx_Idle+0, 0
	MOVF        R0, 0 
	XORLW       1
	BTFSS       STATUS+0, 2 
	GOTO        L_interrupt11
;hae.c,42 :: 		UART1_Write_Text(distanciaCaracter);
	MOVLW       _distanciaCaracter+0
	MOVWF       FARG_UART1_Write_Text_uart_text+0 
	MOVLW       hi_addr(_distanciaCaracter+0)
	MOVWF       FARG_UART1_Write_Text_uart_text+1 
	CALL        _UART1_Write_Text+0, 0
;hae.c,43 :: 		UART1_Write_Text("EOL");
	MOVLW       ?lstr1_hae+0
	MOVWF       FARG_UART1_Write_Text_uart_text+0 
	MOVLW       hi_addr(?lstr1_hae+0)
	MOVWF       FARG_UART1_Write_Text_uart_text+1 
	CALL        _UART1_Write_Text+0, 0
;hae.c,44 :: 		}
L_interrupt11:
;hae.c,45 :: 		}
L_interrupt10:
;hae.c,46 :: 		INTCON.INT0IF = 0;
	BCF         INTCON+0, 1 
;hae.c,47 :: 		INTCON2.INTEDG0 = 1;
	BSF         INTCON2+0, 6 
;hae.c,48 :: 		}
L_interrupt9:
;hae.c,49 :: 		}
L_end_interrupt:
L__interrupt19:
	RETFIE      1
; end of _interrupt

_main:

;hae.c,51 :: 		void main() {
;hae.c,52 :: 		TRISB = 0x01;
	MOVLW       1
	MOVWF       TRISB+0 
;hae.c,53 :: 		TRISC = 0;
	CLRF        TRISC+0 
;hae.c,56 :: 		T0CON = 0X05;
	MOVLW       5
	MOVWF       T0CON+0 
;hae.c,57 :: 		TMR0H = (18661>>8);
	MOVLW       72
	MOVWF       TMR0H+0 
;hae.c,58 :: 		TMR0L = 18661;
	MOVLW       229
	MOVWF       TMR0L+0 
;hae.c,61 :: 		T1CON = 0X80; //In one 16 bit operation
	MOVLW       128
	MOVWF       T1CON+0 
;hae.c,63 :: 		INTCON2.RBPU = 1;
	BSF         INTCON2+0, 7 
;hae.c,64 :: 		INTCON2.INTEDG0 = 1; //Rising edge interrupt
	BSF         INTCON2+0, 6 
;hae.c,65 :: 		INTCON.INT0IF = 0;
	BCF         INTCON+0, 1 
;hae.c,66 :: 		INTCON.INT0IE = 1;
	BSF         INTCON+0, 4 
;hae.c,69 :: 		INTCON.TMR0IF = 0;
	BCF         INTCON+0, 2 
;hae.c,70 :: 		INTCON.TMR0IE = 1;
	BSF         INTCON+0, 5 
;hae.c,71 :: 		INTCON.GIE = 1;
	BSF         INTCON+0, 7 
;hae.c,74 :: 		RCON.IPEN = 0;
	BCF         RCON+0, 7 
;hae.c,75 :: 		SPBRG = 13;
	MOVLW       13
	MOVWF       SPBRG+0 
;hae.c,76 :: 		TXSTA.SYNC = 0;
	BCF         TXSTA+0, 4 
;hae.c,77 :: 		TXSTA.TXEN = 1;
	BSF         TXSTA+0, 5 
;hae.c,78 :: 		TXSTA.TRMT = 1;
	BSF         TXSTA+0, 1 
;hae.c,79 :: 		TXREG = 0;
	CLRF        TXREG+0 
;hae.c,80 :: 		PORTC.B6 = 0;
	BCF         PORTC+0, 6 
;hae.c,81 :: 		UART1_Init(baund_rate);
	MOVLW       51
	MOVWF       SPBRG+0 
	BSF         TXSTA+0, 2, 0
	CALL        _UART1_Init+0, 0
;hae.c,84 :: 		T0CON.TMR0ON = 1;
	BSF         T0CON+0, 7 
;hae.c,85 :: 		while(1);
L_main12:
	GOTO        L_main12
;hae.c,86 :: 		}
L_end_main:
	GOTO        $+0
; end of _main
