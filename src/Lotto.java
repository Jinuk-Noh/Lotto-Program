import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.json.simple.JSONObject;
import org.junit.Test;







public class Lotto extends JFrame implements MouseListener,KeyListener{
	//ToDO 구성요소 설정
	MyButton[] mbt = new MyButton[7]; // 생성자 메소드가 아님
	MyButton mbt8=new MyButton("+");
	
	JLabel titleLbl = new JLabel("로또번호");
	JLabel turnLbl = new JLabel("00회차");
	
	JTextField[] numberTxt = new JTextField[6];
	JButton inputBt = new JButton("입력");
	
//	MyButton mbt1=new MyButton("00");
//	MyButton mbt2=new MyButton("00");
//	MyButton mbt3=new MyButton("00");
//	MyButton mbt4=new MyButton("00");
//	MyButton mbt5=new MyButton("00");
//	MyButton mbt6=new MyButton("00");
//	MyButton mbt7=new MyButton("00");
	
	JTextField turnTxt =new JTextField();
	JButton checkBtn = new JButton("해당회차로");
	
	
	JLabel[] numberLb = new JLabel[6]; 
	JLabel resultLb = new JLabel("결과");
	
	JLabel imgLb =new JLabel(new ImageIcon(new ImageIcon("image/Penguins.jpg").getImage().getScaledInstance(180, 310, Image.SCALE_SMOOTH)));
	
	
	public void init() {
		getContentPane().setLayout(null);
		
		imgLb.setBounds(580, 50,180,310); //이미지
		getContentPane().add(imgLb);
		
		int w = 0; //버튼사이 간격
		for(int i =0;i<mbt.length;i++) {
			mbt[i]= new MyButton("00");
			if(i==6) {
				mbt[i].setBounds(10+w+70,50,60,60);
			}
			else mbt[i].setBounds(10+w, 50, 60, 60);
			getContentPane().add(mbt[i]);
			w+=70;
			//버튼색
			mbt[i].setTextColor(Color.white);
			if(i<2) {
				mbt[i].setBgColor(Color.orange);
			}
			else if(i<4)
				mbt[i].setBgColor(Color.green);
			else if(i<6)
				mbt[i].setBgColor(Color.blue);
			else mbt[i].setBgColor(Color.red);			
		}
					
		mbt8.setTextColor(Color.black);
		mbt8.setBgColor(Color.white);;
		mbt8.setBounds(430, 50, 60, 60);		
		getContentPane().add(mbt8);
		
		titleLbl.setBounds(10,10,150,30);
		getContentPane().add(titleLbl);
//		mbt1.setBounds(10,50,60,60);		
//		getContentPane().add(mbt1);	
//		mbt2.setBounds(80,50,60,60);
//		getContentPane().add(mbt2);	
//		mbt3.setBounds(150,50,60,60);
//		getContentPane().add(mbt3);	
//		mbt4.setBounds(220,50,60,60);
//		getContentPane().add(mbt4);	
//		mbt5.setBounds(290,50,60,60);
//		getContentPane().add(mbt5);	
//		mbt6.setBounds(360,50,60,60);
//		getContentPane().add(mbt6);
		
//		mbt7.setBounds(500,50,60,60);
//		getContentPane().add(mbt7);
				
		turnLbl.setBounds(40,(50+60+30),200,50); //회차라벨
		getContentPane().add(turnLbl);
		//번호입력
		w=0; //텍스트 간격  
		for(int i=0;i<6;i++) {
			numberTxt[i] = new JTextField();
			numberTxt[i].setColumns(10);
			numberTxt[i].setBounds(40+w,200,50,50);
			w=w+70;
			getContentPane().add(numberTxt[i]);
		}
		
		inputBt.setBounds(50+w,200,70,50);
		getContentPane().add(inputBt);
		
		turnTxt.setColumns(10);
		turnTxt.setBounds(40,260,330,50);
		getContentPane().add(turnTxt);
		
		checkBtn.setBounds((230+160),260,150,50);
		getContentPane().add(checkBtn);
		
		// 입력받은 번호 표시 라벨
		w=0; //라벨 간격
		for(int i=0;i<6;i++) {
			numberLb[i] = new JLabel("0");
			numberLb[i].setBounds(40+w,320,50,50);
			getContentPane().add(numberLb[i]);
			w+=70;
		}
		
		// 결과표시 라벨
		resultLb.setBounds(40,380,800,50);
		getContentPane().add(resultLb);
		resultLb.setFont(resultLb.getFont().deriveFont(40f));
			
	}
	
	

	
	
	
	
	public void event() {
		checkBtn.addMouseListener(this);
		turnTxt.addKeyListener(this);	
		
		inputBt.addMouseListener(this);		
		for(int i=0;i<numberTxt.length;i++) {
		numberTxt[i].addKeyListener(this);
		}
	}
	
	//번호 입력하면 라벨에 표시 되게
	void getNumber() {
		for(int i=0;i<numberLb.length;i++) {
			numberLb[i].setText(numberTxt[i].getText());
		}
	}
	//입력한 번호 초기화
		void clearInputNumber(){
			for(int i=0;i<numberTxt.length;i++) {
				numberTxt[i].setText(" ");
				numberLb[i].setText("0");
			}
		}
	//버튼에 있는 번호 초기화
	void clearNumber() {
		for(int i=0;i<mbt.length;i++) {
			mbt[i].setText(" ");
		}
//		mbt1.setText("");
//		mbt2.setText("");
//		mbt3.setText("");
//		mbt4.setText("");
//		mbt5.setText("");
//		mbt6.setText("");
//		mbt7.setText("");
		turnTxt.setText("");
		turnLbl.setText("번호다시 입력해주세요.");
	}	
	
	void showResult() {
		String turnNum = turnTxt.getText();
		try {
			int a =Integer.parseInt(turnNum);
			if(a<0||a>10000) {
				turnLbl.setText("회차를 다시 입력해주세요.");
				turnTxt.setText("");
				return;
			}
		}catch (Exception e) {
			turnLbl.setText("숫자를 입력해주세요.");		
			turnTxt.setText("");			
			return;
		}
		
		try {
		JsonReader jr =new JsonReader();
		JSONObject jo = jr.connectionUrlToJSON(turnTxt.getText());
		if(String.valueOf(jo.get("returnValue")).equals("fail")) {
			turnLbl.setText("회차 정보가 없습니다.");
			turnTxt.setText("");
			return;
		}
		
//		int a1= Integer.parseInt(String.valueOf(jo.get("drwtNo1")));
//		if (a1>40) {
//			
//		}else if( a1>30) {
//		
//		}else {
//			mbt[0].setBgColor(Color.red);
//			mbt[0].setTextColor(Color.green);
//		}
		
		//버튼에 숫자 입력
		for(int i =0;i<mbt.length-1;i++) {	
			mbt[i].setText(String.valueOf(jo.get("drwtNo"+(i+1))));
		}
		mbt[6].setText(String.valueOf(jo.get("bnusNo")));
		
//		mbt[0].setText(String.valueOf(jo.get("drwtNo1")));
//		mbt[1].setText(String.valueOf(jo.get("drwtNo2")));
//		mbt[2].setText(String.valueOf(jo.get("drwtNo3")));
//		mbt[3].setText(String.valueOf(jo.get("drwtNo4")));
//		mbt[4].setText(String.valueOf(jo.get("drwtNo5")));
//		mbt[5].setText(String.valueOf(jo.get("drwtNo6")));
//		mbt[6].setText(String.valueOf(jo.get("bnusNo")));
		
		//입력받은 숫자 범위 별 색표시
		int[] a = new int[7];
		for(int i =0;i<6;i++) {
			a[i]= Integer.parseInt(String.valueOf(jo.get("drwtNo"+(i+1))));	
		}				
		a[6]= Integer.parseInt(String.valueOf(jo.get("bnusNo")));
			
		for(int i=0; i<6;i++)
		{
			if (a[i]>40) {
				mbt[i].setBgColor(Color.pink);
				
			}else if( a[i]>30) {
				mbt[i].setBgColor(Color.magenta);
				
			}else if(a[i]>20) {
				mbt[i].setBgColor(Color.gray);
				
			}else if(a[i]>10) {
				mbt[i].setBgColor(Color.orange);
				
			}else {
				mbt[i].setBgColor(Color.green);
			}
		}
		
	
		// 결과 표시
		String rightNo="";		
		int count=0;
		int count2=0;
		int bnuscount =0;
		String rankStr ="7";
		for(int i=0;i<6;i++) {
			for(int j =0;j<6;j++) {
				if(String.valueOf(a[i]).equals(numberLb[j].getText())){
					rightNo=rightNo+" "+a[i];
					count++;
				}
			}
		}
		for(int i =0;i<6;i++) {
			if(String.valueOf(a[6]).equals(numberLb[i].getText())){
				bnuscount++;			
			}
		}
		count2=count+bnuscount;
		if(bnuscount==1) rightNo=rightNo+" 보너스 번호"+a[6];
		
		if(count==6) {
			rankStr=String.valueOf((Integer.parseInt(rankStr)-count))+"등 경축!";
		}
		else if(count2==6&&bnuscount==1) {
			rankStr=String.valueOf((Integer.parseInt(rankStr)-count2+bnuscount))+"등 축하!";
		}
		else if(count2==5) {
			rankStr=String.valueOf((Integer.parseInt(rankStr)-(count2)+1))+"등 축하~";
		}
		else if(count2>=3){
			rankStr=String.valueOf((Integer.parseInt(rankStr)-(count2)+1))+"등";
		}
		else {
			rankStr="";		
		}	
		
		resultLb.setText("맞은 번호 :" +rightNo +"   총 " + count2+" 개 맞춤"+"  "+rankStr);
		
		turnLbl.setText(turnTxt.getText()+"회차");
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//글자체
	public static void setUIFont(javax.swing.plaf.FontUIResource f) {
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		while(keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value =UIManager.get(key);
			if(value instanceof FontUIResource)
				UIManager.put(key, f);
		}
	}

	Lotto(){
		super("로또번호 조회");
		//화면구성 component 들을 초기화
		init();
		//event를 등록
		event();
		
		setSize(800,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) throws Exception {
		String fType ="NanumPenScript-Regular.ttf";		
		//exception 발생소지가 있으므로 try catch로 묶어주거나.. 내 메소드에 throws Exception을 추가해 줌
		Font f1 = Font.createFont(Font.TRUETYPE_FONT, new File(fType)); 		
		setUIFont(new FontUIResource(f1.deriveFont(25f)));
		// System.out.println("%f \n",25f);
		new Lotto();
		
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			//회차 결과를 버튼 1-7에 보여주기		
			getNumber();
			showResult();
		}
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
			clearNumber();
		}
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
			clearInputNumber();
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void mouseClicked(MouseEvent e) { //누르고 뗏을 때
		if(e.getSource()==checkBtn) {
			//회차 결과를 버튼 1-7에 보여주기	
			getNumber();
			showResult();
			
		}
		if(e.getSource()==inputBt) {
			getNumber();
			showResult();
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent e) { //누르는 시점
	}
	@Override
	public void mouseExited(MouseEvent e) { 
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	
	
}
