package BDD;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;



public class VirtualKeyboard extends JFrame implements KeyListener
{
    private JButton 
            A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,
            Tab,Caps,Shift,Backspace;
    private JTextField textField;
    private String keyText;
    private JLabel label1;
    private JLabel label2;
    private Object[] keys;
    int keycode;

    public VirtualKeyboard()
    {
        super("Typing Application");
        setLayout( new FlowLayout() );
        label1 = new JLabel("Type some text using your keyboard.  The keys you press will be "
                + "highlighed and the text will be displayed");
        add(label1);
        label2 = new JLabel("Note: clicking the buttons with your mouse will not perform any action");
        add(label2);

        textField = new JTextField(60);
        textField.setEditable(true);
        add( textField , BorderLayout.NORTH);

        TextFieldHandler handler = new TextFieldHandler();
    
       
        Backspace = new JButton( "Backspace" );
        add(Backspace);
        Tab = new JButton( "Tab" );
        add(Tab);
        Q = new JButton( "Q" );
        add(Q);
        W = new JButton( "W" );
        add(W);
        E = new JButton( "E" );
        add(E);
        R = new JButton( "R" );
        add(R);
        T = new JButton( "T" );
        add(T);
        Y = new JButton( "Y" );
        add(Y);
        U = new JButton( "U" );
        add(U);
        I = new JButton( "I" );
        add(I);
        O = new JButton( "O" );
        add(O);
        P = new JButton( "P" );
        add(P);

        Caps = new JButton ( "Caps ");
        add(Caps);
        A = new JButton( "A" );
        add(A);
        S = new JButton( "S" );
        add(S);
        D = new JButton( "D" );
        add(D);
        F = new JButton( "F" );
        add(F);
        G = new JButton( "G" );
        add(G);
        H = new JButton( "H" );
        add(H);
        J = new JButton( "J" );
        add(J);
        K = new JButton( "K" );
        add(K);
        L = new JButton( "L" );
        add(L);

        Shift = new JButton ( "Shift" );
        add(Shift);
        Z = new JButton( "Z" );
        add(Z);
        X = new JButton( "X" );
        add(X);
        C = new JButton( "C" );
        add(C);
        V = new JButton( "V" );
        add(V);
        B = new JButton( "B" );
        add(B);
        N = new JButton( "N" );
        add(N);
        M = new JButton( "M" );
        add(M);
        
         VirtualKeyboard typingTutor = new VirtualKeyboard();    // creates TypingTutor
        typingTutor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        typingTutor.setSize(700, 500);      // set dimensions of window
        typingTutor.setVisible(true);
        JFrame frame = new JFrame();
    }

      // overridden keyPressed method handles press of any key
    @Override
    public void keyPressed( KeyEvent event )
    {
        keycode = event.getKeyCode();
        keyText = String.format( "%s",KeyEvent.getKeyText( event.getKeyCode() ) );
        setBackground(Color.PINK);





    }

    // overridden keyReleased method handles press of any key
    @Override
    public void keyReleased( KeyEvent event )
    {
        keycode = event.getKeyCode();
        keyText = String.format( "%s",KeyEvent.getKeyText( event.getKeyCode() ) );
        getBackground();
    }

    @Override
    public void keyTyped( KeyEvent event )
    {
        keyText = String.format( "%s", event.getKeyChar() );

    }


   private class TextFieldHandler implements ActionListener
   {
      // process textfield events
      public void actionPerformed( ActionEvent event )
      {
         String string = ""; // declare string to display

         // user pressed Enter in JTextField textField1
         if ( event.getSource() == textField )
            string = String.format( "%s", event.getActionCommand() );
       }
    }

}

// VirtualKeyboardTest.java 




