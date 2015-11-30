package diningphil;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingWorker;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.concurrent.locks.ReentrantLock;
public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * @wbp.nonvisual location=347,299
	 */
	/**
	 * Launch the application.
	 */
	enum PhilosopherState { Get, Eat, Pon }
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public Main() {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 618, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(118, 191, 100, 88);
		label.setIcon(new ImageIcon("C:\\Users\\CHRIS\\Desktop\\workspace\\diningphil\\Untitled.png"));
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(182, 348, 89, 88);
		label_1.setIcon(new ImageIcon("C:\\Users\\CHRIS\\Desktop\\workspace\\diningphil\\Untitled.png"));
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(274, 83, 96, 100);
		label_2.setIcon(new ImageIcon("C:\\Users\\CHRIS\\Desktop\\workspace\\diningphil\\Untitled.png"));
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(444, 191, 96, 88);
		label_3.setIcon(new ImageIcon("C:\\Users\\CHRIS\\Desktop\\workspace\\diningphil\\Untitled.png"));
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setBounds(381, 348, 84, 88);
		label_4.setIcon(new ImageIcon("C:\\Users\\CHRIS\\Desktop\\workspace\\diningphil\\Untitled.png"));
		contentPane.add(label_4);
		
		
		
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBounds(27, 50, 89, 23);
		contentPane.add(btnExit);
		
		JLabel phil3 = new JLabel("STATUS: Thinking ");
		phil3.setBounds(381, 422, 129, 29);
		contentPane.add(phil3);
		
		JLabel phil2 = new JLabel("STATUS: Thinking ");
		phil2.setBounds(444, 265, 119, 23);
		contentPane.add(phil2);
		
		JLabel phil1 = new JLabel("STATUS: Thinking ");
		phil1.setBounds(274, 167, 119, 29);
		contentPane.add(phil1);
		
		JLabel phil5 = new JLabel("STATUS: Thinking ");
		phil5.setBounds(118, 265, 134, 23);
		contentPane.add(phil5);
		
		JLabel phil4 = new JLabel("STATUS: Thinking ");
		phil4.setBounds(182, 422, 119, 23);
		contentPane.add(phil4);
		
		JLabel f1 = new JLabel("");
		f1.setBounds(420, 130, 24, 54);
		f1.setIcon(new ImageIcon("C:\\Users\\CHRIS\\Desktop\\workspace\\diningphil\\Untitled1.png"));
		contentPane.add(f1);
		
		JLabel f3 = new JLabel("");
		f3.setBounds(311, 382, 24, 54);
		f3.setIcon(new ImageIcon("C:\\Users\\CHRIS\\Desktop\\workspace\\diningphil\\Untitled1.png"));
		contentPane.add(f3);
		
		JLabel f4 = new JLabel("");
		f4.setBounds(157, 288, 24, 54);
		f4.setIcon(new ImageIcon("C:\\Users\\CHRIS\\Desktop\\workspace\\diningphil\\Untitled1.png"));
		contentPane.add(f4);
		
		JLabel f5 = new JLabel("");
		f5.setBounds(211, 134, 24, 54);
		f5.setIcon(new ImageIcon("C:\\Users\\CHRIS\\Desktop\\workspace\\diningphil\\Untitled1.png"));
		contentPane.add(f5);
		
		JLabel f2 = new JLabel("");
		f2.setBounds(486, 285, 24, 54);
		f2.setIcon(new ImageIcon("C:\\Users\\CHRIS\\Desktop\\workspace\\diningphil\\Untitled1.png"));
		contentPane.add(f2);
		JLabel f[]={f1,f2,f3,f4,f5};
		JLabel stat[]={phil1,phil2,phil3,phil4,phil5};
		class Philosopher implements Runnable {

		    private ReentrantLock leftChopStick;
		    private ReentrantLock rightChopStick;
		    private int Id;

		    public AtomicBoolean isTummyFull=new AtomicBoolean(false);

		    //To randomize eat/Think time
		    private Random randomGenerator = new Random();

		    private int noOfTurnsToEat=0;

		    public int getId(){
		        return this.Id;
		    }
		    public int getNoOfTurnsToEat(){
		        return noOfTurnsToEat;
		    }

		    /****
		     * 
		     * @param id Philosopher number
		     * 
		     * @param leftChopStick
		     * @param rightChopStick
		     */
		    
		    public Philosopher(int id, ReentrantLock leftChopStick, ReentrantLock rightChopStick) {
		        this.Id = id;
		        this.leftChopStick = leftChopStick;
		        this.rightChopStick = rightChopStick;
		    }

		    @Override
		    public void run() {

		        for(int i=0;i<5;i++) {
		            try {
		                think();
		                Thread.sleep(1000);
		                if (pickupLeftChopStick() && pickupRightChopStick()) {
		                    eat();
		                    Thread.sleep(1000);
		                }
		                putDownChopSticks();
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		        }
		        stat[Id].setText("Status: done");
		    }

		    private void think() throws InterruptedException {
		        System.out
		                .println(String.format("Philosopher %s is thinking", this.Id));

		        stat[Id].setText("Status:Thinking");
		        System.out.flush();
		        Thread.sleep(randomGenerator.nextInt(1000));
		    }

		    private void eat() throws InterruptedException {
		        System.out.println(String.format("Philosopher %s is eating", this.Id));
		        stat[Id].setText("Status:Eating");
		        System.out.flush();
		        
		        noOfTurnsToEat++;
		        Thread.sleep(randomGenerator.nextInt(1000));
		    }

		    private boolean pickupLeftChopStick() throws InterruptedException {
		        if (leftChopStick.tryLock(10, TimeUnit.MILLISECONDS)) {
		            System.out.println(String.format(
		                    "Philosopher %s pickedup Left ChopStick", this.Id));
		            f[Id].setVisible(false);
		            System.out.flush();
		            return true;
		        }
		        return false;
		    }

		    private boolean pickupRightChopStick() throws InterruptedException {
		        if (rightChopStick.tryLock(10, TimeUnit.MILLISECONDS)) {
		            System.out.println(String.format(
		                    "Philosopher %s pickedup Right ChopStick", this.Id));
		            f[(Id+4)%5].setVisible(false);
		            System.out.flush();
		            return true;
		        }
		        return false;
		    }

		    private void putDownChopSticks() {
		        if (leftChopStick.isHeldByCurrentThread()) {
		            leftChopStick.unlock();
		            System.out.println(String.format(
		                    "Philosopher %s putdown Left ChopStick", this.Id));
		            f[Id].setVisible(true);
		            System.out.flush();
		        }
		        if (rightChopStick.isHeldByCurrentThread()) {
		            rightChopStick.unlock();
		            System.out.println(String.format(
		                    "Philosopher %s putdown Right ChopStick", this.Id));
		            f[(Id+4)%5].setVisible(true);
		            System.out.flush();
		        }
		    }
		}
		 
				
		JButton btnStart = new JButton("START");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingWorker<Object, Object> worker = new SwingWorker<Object, Object>() {

					@Override
					protected Object doInBackground() throws Exception {
						
						final int NO_OF_PHILOSOPHER = 5;
					    final int SIMULATION_MILLIS = 1000*60*8;

					    
					        ExecutorService executorService = null;

					        Philosopher[] philosophers = null;
					        try {

					            philosophers = new Philosopher[NO_OF_PHILOSOPHER];

					            //As many forks as Philosophers
					            ReentrantLock[] forks = new ReentrantLock[NO_OF_PHILOSOPHER];
					            Arrays.fill(forks, new ReentrantLock());

					            executorService = Executors.newFixedThreadPool(NO_OF_PHILOSOPHER);

					            for (int i = 0; i < NO_OF_PHILOSOPHER; i++) {
					                philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1)
					                        % NO_OF_PHILOSOPHER]);
					                executorService.execute(philosophers[i]);
					            }
					            //Main thread sleeps till time of simulation
					            Thread.sleep(SIMULATION_MILLIS);
					            for (Philosopher philosopher : philosophers) {
					                philosopher.isTummyFull.set(true);
					            }
					            //all philosophers are done eating...

					        } finally {
					            executorService.shutdown();

					            // Wait period for all thread to finish
					            Thread.sleep(1000);

					            //Time for check
					            for (Philosopher philosopher : philosophers) {
					                System.out.println("Philosopher (" + philosopher.getId()
					                        + ") =>No of Turns to Eat ="
					                        + philosopher.getNoOfTurnsToEat());
					                System.out.flush();
					            }
					        }
					    
						return null;
					}
				};
				
				worker.execute();
			}
		});
		btnStart.setBounds(27, 16, 89, 23);
		contentPane.add(btnStart);
	}
}
