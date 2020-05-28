package Game;
import java.util.List;

import javax.swing.ImageIcon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * This class represents a Board that can be used in a collection
 * of solitaire games similar to Elevens.  The variants differ in
 * card removal and the board size.
 * In this CASE: HIGH OR LOW GAME
 */
public class Board {
	/*
	 * Declare all private instance variables such as card ranks, suits, and point values
	 */
	private static final int BOARD_SIZE = 1;
	
	/*
	 * Options given for the player to choose from
	 */
	private static final String[] OPTIONS = {"HIGHER", "LOWER", "Exit"};
	private static final String[] INKLING = {"Inkling Girl", "Inkling Boy "};

	private static final String[] RANKS =
		{"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
	private static final String[] SUITS =
		{"spades", "hearts", "diamonds", "clubs"};

	private static final int[] POINT_VALUES =
		{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
	private String tier = "C";
	private ImageIcon test =  new ImageIcon((getClass().getResource("ninja fat boy.jpg")));

	//All GUI to be used in the game
	private ImageIcon currPic;// =  new ImageIcon((getClass().getResource("2CLUBS.png")));
	private ImageIcon nextPic;// =  new ImageIcon((getClass().getResource("2CLUBS.png")));
	private ImageIcon home =  new ImageIcon((getClass().getResource("Splat.png")));
	private ImageIcon boysad =  new ImageIcon((getClass().getResource("boy.png")));
	private ImageIcon avatar =  new ImageIcon((getClass().getResource("both.png")));
	private ImageIcon girlsad =  new ImageIcon((getClass().getResource("girlsad.png")));
	private ImageIcon boyhappy =  new ImageIcon((getClass().getResource("happy boy.png")));
	private ImageIcon girlhappy =  new ImageIcon((getClass().getResource("happy girl.jpg")));
	private ImageIcon board =  new ImageIcon((getClass().getResource("cards.png")));
	private ImageIcon sis =  new ImageIcon((getClass().getResource("calmar.png")));
	private ImageIcon rank =  new ImageIcon((getClass().getResource("start.jpg")));
	private ImageIcon lobby =  new ImageIcon((getClass().getResource("lobby.png")));

	private ImageIcon cPlus =  new ImageIcon((getClass().getResource("C+.png")));
	private ImageIcon bMinus =  new ImageIcon((getClass().getResource("B-.png")));
	private ImageIcon b =  new ImageIcon((getClass().getResource("B.png")));
	private ImageIcon bPlus =  new ImageIcon((getClass().getResource("B+.png")));
	private ImageIcon aMinus =  new ImageIcon((getClass().getResource("A-.png")));
	private ImageIcon a =  new ImageIcon((getClass().getResource("A.png")));
	private ImageIcon aPlus =  new ImageIcon((getClass().getResource("A+.png")));
	private ImageIcon s =  new ImageIcon((getClass().getResource("S.png")));
	private ImageIcon sPlus =  new ImageIcon((getClass().getResource("S+.png")));
	private ImageIcon x =  new ImageIcon((getClass().getResource("X.png")));
	private ImageIcon rewards =  new ImageIcon((getClass().getResource("rewards.png")));

	/*
	 * Player will be given option to choose from whether they want to be a boy or a girl
	 * Booleans used to determine their choices
	 */
	
	boolean boy = false;
	boolean girl = false;
	/**
	 * The cards on this board.
	 */
	private Card[] cards;
	
	private HashMap<String, Integer> pics;
	private List keylist;
	/**
	 * The deck of cards being used to play the current game.
	 */
	private Deck deck;

	/*
	 * Ints used to determine the score; how many correct and incorrect
	 */
	int correct = 0;
	int loss = 0;
	int numCorrect = 0;
	int cntCorr = 0;
	
	//Getters
	public int getCorrect() {
		return correct;
	}
	public int getLoss() {
		return loss;
	}
	
	/*
	 * Constructor where the game takes place 26 times; two cards will be displayed at the same time so 52/2 = 26
	 */
	public Board() {
		deck = new Deck(RANKS, SUITS, POINT_VALUES);
		Window.msg2(null, test);
		deck.shuffle();
		int cnt1  = 0;
		Window.msg2("Welcome to Metropolis, home of the INKLINGS!", home);
        int choose = Window.option1(INKLING, "Choose your Inkling!", avatar);
           gender(choose);
             begin();
             
             //This is where the game actually starts
		for(int i = 0; i< 26; i++) {
			win();
			System.out.println("i = " +i);
				Card curr = deck.deal();
				cnt1++;
				currImage(curr);//Sets up the image for current card
				System.out.println(cnt1);
				
				//Asks whether player thinks the current card is higher or lower than the next card
				int x =Window.option1(OPTIONS, "RANK: " + tier+"\nCORRECT: "+ getCorrect() +  " --- INCORRECT: "+ getLoss()
						+ "\nThe current card is: "+ curr + "\n Is the current card higher or lower than the next card?", currPic);
				
				/*
				 * EXIT BUTTON
				 */
				if(x==2) {
					end();
					System.exit(0);
				}
				
				Card next = deck.deal();
				cnt1++;
				nextImage(next);//Sets up the image for the next card
				System.out.println(cnt1);
				Window.msg2("The next card is: "+ next, nextPic);
				
				//If the player chooses that the current card is higher
				if(x==0) {
					
					//If current and next card have the same pointvalue, nothing is affected
					if(curr.pointValue() == next.pointValue()) {
						Window.msg("The current card: "+curr + " matches the next card: "+ next
								+ "\n Your rank was not affected!", "RANK: " + tier+ "(CORRECT: "+ getCorrect() +  " --- INCORRECT: "+ getLoss()+")");
					}
					
					//If current card is less than the next card but player chose higher; INCORRECT
					else if(curr.pointValue()<next.pointValue()) {
						if(boy) {
						Window.msg2("Oh no, the next card: " + next + ", is higher than the current card: " + curr + "!",boysad);
						}
						else if(girl) {
							Window.msg2("Oh no, the next card: " + next + ", is higher than the current card: " + curr + "!",girlsad);
						}
						loss++;
						cntCorr--;
						numCorrect--;
						rankDown();
						Window.msg("You got oOFeD!"
								+ " Your rank has been affected...","RANK: " + tier+"(CORRECT: "+ getCorrect() +  " --- INCORRECT: "+ getLoss()+")");
					}
					
					//If current card is greater than the next card but player chose higher; CORRECT
					else if(curr.pointValue()>next.pointValue()) {
						if(boy) {
							Window.msg2("Awesome, the current card: "+ curr + ", is higher than the next card: "+ next+ "!",boyhappy);
							}
							else if(girl) {
								Window.msg2("Awesome, the current card: "+ curr + ", is higher than the next card: "+ next+ "!", girlhappy);
							}
						correct++;
						cntCorr++;
						numCorrect++;
						rank();
						Window.msg("You got a Victory! You are now boosting your rank!"
								+ "","RANK:" + tier+"(CORRECT: "+ getCorrect() +  " --- INCORRECT: "+ getLoss()+")");
					}
				}
				
				//If the player chooses that the current card is lower
				if(x==1) {
					
					//If current and next card have the same pointvalue, nothing is affected
					if(curr.pointValue() == next.pointValue()) {
						Window.msg("The current card: "+curr + " matches the next card: "+ next
								+ "\n Your rank was not affected!", "RANK: " + tier+ "(CORRECT: "+ getCorrect() +  " --- INCORRECT: "+ getLoss()+")");
					}

					//If current card is greater than the next card but player chose lower; INCORRECT
					else if(curr.pointValue()>next.pointValue()) {
						if(boy) {
							Window.msg2("Oh no, the next card: " + next + ", is lower than the current card: " + curr + "!", boysad);
							}
							else if(girl) {
								Window.msg2("Oh no, the next card: " + next + ", is lower than the current card: " + curr + "!", girlsad);
							}
						loss++;
						numCorrect--;
						cntCorr--;
						rankDown();
						Window.msg("You got oOFeD! Your rank has been affected...","RANK:" + tier+"(CORRECT: "+ getCorrect() +  " --- INCORRECT: "+ getLoss()+")");
					}

					//If current card is less than the next card but player chose lower; CORRECT
					else if(curr.pointValue()<next.pointValue()) {
						if(boy) {
							Window.msg2("Awesome, the current card: "+ curr + ", is lower than the next card: "+ next+ "!", boyhappy);
							}
						else if(girl) {
							Window.msg2("Awesome, the current card: "+ curr + ", is lower than the next card: "+ next+ "!", girlhappy);
							}
						correct++;
						numCorrect++;
						cntCorr++;
						rank();
						Window.msg("You got a Victory! You are now boosting your rank!",
								"RANK: " + tier+"(CORRECT: "+ getCorrect() +  " --- INCORRECT: "+ getLoss()+")");
					}
				}
			
			//Handles the End of the Game
			lose();
			win();
		}
	}
	
	/*
	 * Ending message
	 */
	public void end() {
		Window.msg2("Your squid kid instincts have overwhelmed you.....You have decided to abandon this Metropolis."
				+ "\nReturning back to LOBBY and EXITING.", lobby);
		Window.msg3("Credits: All supported by the power of JOptionPane!"
				+ "\n The price of playing this game will be a LEADERBOARD POINT!");
	}
	
	/*
	 * This is where the ranking system is calculated
	 * The numCorrect int variable determines your tier/rank when you get something wrongs
	 */
	public void rankDown() {
		if(numCorrect<0) {
			numCorrect = 0;
		}
	else if(numCorrect == 0) {
		tier = "C";
		Window.msg3("Awwww you went down to C...");
		}
		else if(numCorrect ==1) {
			tier = "C+";
			Window.msg3("Awwww you went down to C+...!");
		}
		else if(numCorrect ==2) {
			tier = "B-";
			Window.msg3("Awwww you went down to B-...!");
		}
		else if(numCorrect ==3) {
			tier = "B";
			Window.msg3("Awwww you went down to B...!");
		}
		else if(numCorrect ==4) {
			tier = "B+";
			Window.msg3("Awwww you went down to B+...!");
		}
		else if(numCorrect ==5) {
			tier = "A-";
			Window.msg3("Awwww you went down to A-...!");
		}
		else if(numCorrect ==6) {
			tier = "A";
			Window.msg3("Awwww you went down to A...!");
		}
		else if(numCorrect ==7) {
			tier = "A+";
			Window.msg3("Awwww you went down to A+...!");
		}
		else if(numCorrect ==8) {
			tier = "S";
			Window.msg3("Awwww you went down to S...!");
		}
		else if(numCorrect == 9) {
			tier = "S+";
			Window.msg3("Awwww you went down to S+...");
		}
	}
	
	/*
	 * This method covers the part when a player gets something correct and moves up a rank/tier
	 */
	public void rank() {
	if(cntCorr == 1) {
		tier = "C+";
		Window.msg2("Rank Up: C+!", cPlus);
		}
		else if(cntCorr ==2) {
			tier = "B-";
			Window.msg2("Rank Up: B-!", bMinus);
		}
		else if(cntCorr ==3) {
			tier = "B";
			Window.msg2("Rank Up: B!", b);
		}
		else if(cntCorr ==4) {
			tier = "B+";
			Window.msg2("Rank Up: B+!", bPlus);
		}
		else if(cntCorr ==5) {
			tier = "A-";
			Window.msg2("Rank Up: A-!", aMinus);
		}
		else if(cntCorr ==6) {
			tier = "A";
			Window.msg2("Rank Up: A!", a);
		}
		else if(cntCorr ==7) {
			tier = "A+";
			Window.msg2("Rank Up: A+!", aPlus);
		}
		else if(cntCorr ==8) {
			tier = "S";
			Window.msg2("Rank Up: S!", s);
		}
		else if(cntCorr == 9) {
			tier = "S+";
			Window.msg2("Rank Up: S+!", sPlus);
		}
		else if(cntCorr == 10) {
			tier = "X";
			Window.msg2("Rank Up: X! \n WOW, you've made it to the final rank, now everything is free for all!", x);
		}
		else if(cntCorr >=10) {
			cntCorr = 10;
		}
	}
	
	/*
	 * Beginning scenes/Introduction
	 */
	public void begin() {
		Window.msg2("Entering RANK MODE: HIGH LOW"
         		+ "\n Callie and Marie will get you up to speed.", board);
         Window.msg2("Yo waddup gang, its the SQUID SISTERS coming to help you! The HIGH LOW game involves you choosing"
         		+ "\n whether your current card is higher or lower than the next card!", sis);
         Window.msg2("Rank system is as usual... try to get to X RANK, the VICTORIES boost you up, OOF make brings you DOWN!!!"
         		+ "\n You start at the lowest rank, RANK C!"
         		+ "\n***3Losses and you OUT***", rank);
	}
	
	/*
	 * This method is a Setter based on what the person chooses
	 */
	public void gender(int choose) {
		  if(choose == 1) {
         	 boy = true;
          }
          else {
         	 girl = true;
          }
	}
	
	/*
	 * This method, based on the pointValue of the card and the suit, determines what ImageIcon to set to currPic
	 *  Basically choose what the pic of the current card when given a Card parameter
	 * GUI ASPECT
	 */
	public void currImage(Card c) {
		if(c.pointValue() == 1) {
			if(c.suit() == "clubs") {
				currPic = new ImageIcon((getClass().getResource("AClubs.png")));
			}
			else if(c.suit() == "hearts") {
				currPic = new ImageIcon((getClass().getResource("AHearts.png")));
			}
			else if(c.suit() == "diamonds") {
				currPic = new ImageIcon((getClass().getResource("ADiamonds.png")));
			}
			else if(c.suit() == "spades") {
				currPic = new ImageIcon((getClass().getResource("ASpades.png")));
			}
		}
		if(c.pointValue() == 2) {
			if(c.suit() == "clubs") {
				currPic = new ImageIcon((getClass().getResource("2Clubs.png")));
			}
			else if(c.suit() == "hearts") {
				currPic = new ImageIcon((getClass().getResource("2Hearts.png")));
			}
			else if(c.suit() == "diamonds") {
				currPic = new ImageIcon((getClass().getResource("2Diamonds.png")));
			}
			else if(c.suit() == "spades") {
				currPic = new ImageIcon((getClass().getResource("2Spades.png")));
			}
		}
		if(c.pointValue() == 3) {
			if(c.suit() == "clubs") {
				currPic = new ImageIcon((getClass().getResource("3Clubs.png")));
			}
			else if(c.suit() == "hearts") {
				currPic = new ImageIcon((getClass().getResource("3Hearts.png")));
			}
			else if(c.suit() == "diamonds") {
				currPic = new ImageIcon((getClass().getResource("3Diamonds.png")));
			}
			else if(c.suit() == "spades") {
				currPic = new ImageIcon((getClass().getResource("3Spades.png")));
			}
		}
		if(c.pointValue() == 4) {
			if(c.suit() == "clubs") {
				currPic = new ImageIcon((getClass().getResource("4Clubs.png")));
			}
			else if(c.suit() == "hearts") {
				currPic = new ImageIcon((getClass().getResource("4Hearts.png")));
			}
			else if(c.suit() == "diamonds") {
				currPic = new ImageIcon((getClass().getResource("4Diamonds.png")));
			}
			else if(c.suit() == "spades") {
				currPic = new ImageIcon((getClass().getResource("4Spades.png")));
			}
		}
		if(c.pointValue() == 5) {
			if(c.suit() == "clubs") {
				currPic = new ImageIcon((getClass().getResource("5Clubs.png")));
			}
			else if(c.suit() == "hearts") {
				currPic = new ImageIcon((getClass().getResource("5Hearts.png")));
			}
			else if(c.suit() == "diamonds") {
				currPic = new ImageIcon((getClass().getResource("5Diamonds.png")));
			}
			else if(c.suit() == "spades") {
				currPic = new ImageIcon((getClass().getResource("5Spades.png")));
			}
		}
		if(c.pointValue() == 6) {
			if(c.suit() == "clubs") {
				currPic = new ImageIcon((getClass().getResource("6Clubs.png")));
			}
			else if(c.suit() == "hearts") {
				currPic = new ImageIcon((getClass().getResource("6Hearts.png")));
			}
			else if(c.suit() == "diamonds") {
				currPic = new ImageIcon((getClass().getResource("6Diamonds.png")));
			}
			else if(c.suit() == "spades") {
				currPic = new ImageIcon((getClass().getResource("6Spades.png")));
			}
		}
		if(c.pointValue() == 7) {
			if(c.suit() == "clubs") {
				currPic = new ImageIcon((getClass().getResource("7Clubs.png")));
			}
			else if(c.suit() == "hearts") {
				currPic = new ImageIcon((getClass().getResource("7Hearts.png")));
			}
			else if(c.suit() == "diamonds") {
				currPic = new ImageIcon((getClass().getResource("7Diamonds.png")));
			}
			else if(c.suit() == "spades") {
				currPic = new ImageIcon((getClass().getResource("7Spades.png")));
			}
		}
		if(c.pointValue() == 8) {
			if(c.suit() == "clubs") {
				currPic = new ImageIcon((getClass().getResource("8Clubs.png")));
			}
			else if(c.suit() == "hearts") {
				currPic = new ImageIcon((getClass().getResource("8Hearts.png")));
			}
			else if(c.suit() == "diamonds") {
				currPic = new ImageIcon((getClass().getResource("8Diamonds.png")));
			}
			else if(c.suit() == "spades") {
				currPic = new ImageIcon((getClass().getResource("8Spades.png")));
			}
		}
		if(c.pointValue() == 9) {
			if(c.suit() == "clubs") {
				currPic = new ImageIcon((getClass().getResource("9Clubs.png")));
			}
			else if(c.suit() == "hearts") {
				currPic = new ImageIcon((getClass().getResource("9Hearts.png")));
			}
			else if(c.suit() == "diamonds") {
				currPic = new ImageIcon((getClass().getResource("9Diamonds.png")));
			}
			else if(c.suit() == "spades") {
				currPic = new ImageIcon((getClass().getResource("9Spades.png")));
			}
		}
		if(c.pointValue() == 10) {
			if(c.suit() == "clubs") {
				currPic = new ImageIcon((getClass().getResource("10Clubs.png")));
			}
			else if(c.suit() == "hearts") {
				currPic = new ImageIcon((getClass().getResource("10Hearts.png")));
			}
			else if(c.suit() == "diamonds") {
				currPic = new ImageIcon((getClass().getResource("10Diamonds.png")));
			}
			else if(c.suit() == "spades") {
				currPic = new ImageIcon((getClass().getResource("10Spades.png")));
			}
		}
		if(c.pointValue() == 11) {
			if(c.suit() == "clubs") {
				currPic = new ImageIcon((getClass().getResource("JClubs.png")));
			}
			else if(c.suit() == "hearts") {
				currPic = new ImageIcon((getClass().getResource("JHearts.png")));
			}
			else if(c.suit() == "diamonds") {
				currPic = new ImageIcon((getClass().getResource("JDiamonds.png")));
			}
			else if(c.suit() == "spades") {
				currPic = new ImageIcon((getClass().getResource("JSpades.png")));
			}
		}
		if(c.pointValue() == 12) {
			if(c.suit() == "clubs") {
				currPic = new ImageIcon((getClass().getResource("QClubs.png")));
			}
			else if(c.suit() == "hearts") {
				currPic = new ImageIcon((getClass().getResource("QHearts.png")));
			}
			else if(c.suit() == "diamonds") {
				currPic = new ImageIcon((getClass().getResource("QDiamonds.png")));
			}
			else if(c.suit() == "spades") {
				currPic = new ImageIcon((getClass().getResource("QSpades.png")));
			}
		}
		if(c.pointValue() == 13) {
			if(c.suit() == "clubs") {
				currPic = new ImageIcon((getClass().getResource("KClubs.png")));
			}
			else if(c.suit() == "hearts") {
				currPic = new ImageIcon((getClass().getResource("KHearts.png")));
			}
			else if(c.suit() == "diamonds") {
				currPic = new ImageIcon((getClass().getResource("KDiamonds.png")));
			}
			else if(c.suit() == "spades") {
				currPic = new ImageIcon((getClass().getResource("KSpades.png")));
			}
		}
	}
	
	/*
	 * This method, based on the pointValue of the card and the suit, determines what ImageIcon to set to nextPic
	 * Basically choose what the pic of the next card when given a Card parameter
	 * GUI ASPECT
	 */
	public void nextImage(Card c) {
		if(c.pointValue() == 1) {
			if(c.suit() == "clubs") {
				nextPic = new ImageIcon((getClass().getResource("AClubs.png")));
			}
			else if(c.suit() == "hearts") {
				nextPic = new ImageIcon((getClass().getResource("AHearts.png")));
			}
			else if(c.suit() == "diamonds") {
				nextPic = new ImageIcon((getClass().getResource("ADiamonds.png")));
			}
			else if(c.suit() == "spades") {
				nextPic = new ImageIcon((getClass().getResource("ASpades.png")));
			}
		}
		if(c.pointValue() == 2) {
			if(c.suit() == "clubs") {
				nextPic = new ImageIcon((getClass().getResource("2Clubs.png")));
			}
			else if(c.suit() == "hearts") {
				nextPic = new ImageIcon((getClass().getResource("2Hearts.png")));
			}
			else if(c.suit() == "diamonds") {
				nextPic = new ImageIcon((getClass().getResource("2Diamonds.png")));
			}
			else if(c.suit() == "spades") {
				nextPic = new ImageIcon((getClass().getResource("2Spades.png")));
			}
		}
		if(c.pointValue() == 3) {
			if(c.suit() == "clubs") {
				nextPic = new ImageIcon((getClass().getResource("3Clubs.png")));
			}
			else if(c.suit() == "hearts") {
				nextPic = new ImageIcon((getClass().getResource("3Hearts.png")));
			}
			else if(c.suit() == "diamonds") {
				nextPic = new ImageIcon((getClass().getResource("3Diamonds.png")));
			}
			else if(c.suit() == "spades") {
				nextPic = new ImageIcon((getClass().getResource("3Spades.png")));
			}
		}
		if(c.pointValue() == 4) {
			if(c.suit() == "clubs") {
				nextPic = new ImageIcon((getClass().getResource("4Clubs.png")));
			}
			else if(c.suit() == "hearts") {
				nextPic = new ImageIcon((getClass().getResource("4Hearts.png")));
			}
			else if(c.suit() == "diamonds") {
				nextPic = new ImageIcon((getClass().getResource("4Diamonds.png")));
			}
			else if(c.suit() == "spades") {
				nextPic = new ImageIcon((getClass().getResource("4Spades.png")));
			}
		}
		if(c.pointValue() == 5) {
			if(c.suit() == "clubs") {
				nextPic = new ImageIcon((getClass().getResource("5Clubs.png")));
			}
			else if(c.suit() == "hearts") {
				nextPic = new ImageIcon((getClass().getResource("5Hearts.png")));
			}
			else if(c.suit() == "diamonds") {
				nextPic = new ImageIcon((getClass().getResource("5Diamonds.png")));
			}
			else if(c.suit() == "spades") {
				nextPic = new ImageIcon((getClass().getResource("5Spades.png")));
			}
		}
		if(c.pointValue() == 6) {
			if(c.suit() == "clubs") {
				nextPic = new ImageIcon((getClass().getResource("6Clubs.png")));
			}
			else if(c.suit() == "hearts") {
				nextPic = new ImageIcon((getClass().getResource("6Hearts.png")));
			}
			else if(c.suit() == "diamonds") {
				nextPic = new ImageIcon((getClass().getResource("6Diamonds.png")));
			}
			else if(c.suit() == "spades") {
				nextPic = new ImageIcon((getClass().getResource("6Spades.png")));
			}
		}
		if(c.pointValue() == 7) {
			if(c.suit() == "clubs") {
				nextPic = new ImageIcon((getClass().getResource("7Clubs.png")));
			}
			else if(c.suit() == "hearts") {
				nextPic = new ImageIcon((getClass().getResource("7Hearts.png")));
			}
			else if(c.suit() == "diamonds") {
				nextPic = new ImageIcon((getClass().getResource("7Diamonds.png")));
			}
			else if(c.suit() == "spades") {
				nextPic = new ImageIcon((getClass().getResource("7Spades.png")));
			}
		}
		if(c.pointValue() == 8) {
			if(c.suit() == "clubs") {
				nextPic = new ImageIcon((getClass().getResource("8Clubs.png")));
			}
			else if(c.suit() == "hearts") {
				nextPic = new ImageIcon((getClass().getResource("8Hearts.png")));
			}
			else if(c.suit() == "diamonds") {
				nextPic = new ImageIcon((getClass().getResource("8Diamonds.png")));
			}
			else if(c.suit() == "spades") {
				nextPic = new ImageIcon((getClass().getResource("8Spades.png")));
			}
		}
		if(c.pointValue() == 9) {
			if(c.suit() == "clubs") {
				nextPic = new ImageIcon((getClass().getResource("9Clubs.png")));
			}
			else if(c.suit() == "hearts") {
				nextPic = new ImageIcon((getClass().getResource("9Hearts.png")));
			}
			else if(c.suit() == "diamonds") {
				nextPic = new ImageIcon((getClass().getResource("9Diamonds.png")));
			}
			else if(c.suit() == "spades") {
				nextPic = new ImageIcon((getClass().getResource("9Spades.png")));
			}
		}
		if(c.pointValue() == 10) {
			if(c.suit() == "clubs") {
				nextPic = new ImageIcon((getClass().getResource("10Clubs.png")));
			}
			else if(c.suit() == "hearts") {
				nextPic = new ImageIcon((getClass().getResource("10Hearts.png")));
			}
			else if(c.suit() == "diamonds") {
				nextPic = new ImageIcon((getClass().getResource("10Diamonds.png")));
			}
			else if(c.suit() == "spades") {
				nextPic = new ImageIcon((getClass().getResource("10Spades.png")));
			}
		}
		if(c.pointValue() == 11) {
			if(c.suit() == "clubs") {
				nextPic = new ImageIcon((getClass().getResource("JClubs.png")));
			}
			else if(c.suit() == "hearts") {
				nextPic = new ImageIcon((getClass().getResource("JHearts.png")));
			}
			else if(c.suit() == "diamonds") {
				nextPic = new ImageIcon((getClass().getResource("JDiamonds.png")));
			}
			else if(c.suit() == "spades") {
				nextPic = new ImageIcon((getClass().getResource("JSpades.png")));
			}
		}
		if(c.pointValue() == 12) {
			if(c.suit() == "clubs") {
				nextPic = new ImageIcon((getClass().getResource("QClubs.png")));
			}
			else if(c.suit() == "hearts") {
				nextPic = new ImageIcon((getClass().getResource("QHearts.png")));
			}
			else if(c.suit() == "diamonds") {
				nextPic = new ImageIcon((getClass().getResource("QDiamonds.png")));
			}
			else if(c.suit() == "spades") {
				nextPic = new ImageIcon((getClass().getResource("QSpades.png")));
			}
		}
		if(c.pointValue() == 13) {
			if(c.suit() == "clubs") {
				nextPic = new ImageIcon((getClass().getResource("KClubs.png")));
			}
			else if(c.suit() == "hearts") {
				nextPic = new ImageIcon((getClass().getResource("KHearts.png")));
			}
			else if(c.suit() == "diamonds") {
				nextPic = new ImageIcon((getClass().getResource("KDiamonds.png")));
			}
			else if(c.suit() == "spades") {
				nextPic = new ImageIcon((getClass().getResource("KSpades.png")));
			}
		}
	}

	/*
	 * Handles all losing scenes and ends the game
	 */
	public void lose() {
		if(loss>=3) {
			Window.msg2("Your losses have overwhelmed you.....You have been forced to leave."
					+ "\nReturning back to LOBBY and EXITING.", lobby);
			Window.msg3("ScoreBoard<>"
					+ "\nYour final rank was "+ tier + ". "
							+ "\nCORRECT: "+ getCorrect() + " --- INCORRECT: "+ getLoss()
							+ "\nSTAY FRESH!");
			Window.msg3("Credits: All supported by the power of JOptionPane!"
					+ "\n The price of playing this game will be a LEADERBOARD POINT!");
			System.exit(0);
		}
	}
	
	/*
	 * Handles all winning scenes and ends the game
	 */
	public void win() {
		if(deck.size() <= 0) {
			Window.msg2("What you won...You earned these fresh prizes!"
					+ "\n Check out OctoExpansion DLC for more info on using dem fresh Gearz!", rewards);
			Window.msg3("ScoreBoard<>"
					+ "\nYour final rank was "+ tier + ". "
							+ "\nCORRECT: "+ getCorrect() + " --- INCORRECT: "+ getLoss()
							+ "\nSTAY FRESH!");
			Window.msg3("Credits: All supported by the power of JOptionPane!"
					+ "\n The price of playing this game will be a LEADERBOARD POINT!");
			System.exit(0);
		}
		
	}
	/**
	 * Start a new game by shuffling the deck and
	 * dealing some cards to this board.
	 */
	public void newGame() {
		deck.shuffle();
		//dealMyCards();
	}

	/**
	 * Accesses the size of the board.
	 * Note that this is not the number of cards it contains,
	 * which will be smaller near the end of a winning game.
	 * @return the size of the board
	 */
	public int size() {
		return cards.length;
	}

	/**
	 * Determines if the board is empty (has no cards).
	 * @return true if this board is empty; false otherwise.
	 */
	public boolean isEmpty() {
		for (int k = 0; k < cards.length; k++) {
			if (cards[k] != null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Deal a card to the kth position in this board.
	 * If the deck is empty, the kth card is set to null.
	 * @param k the index of the card to be dealt.
	 */
//	public void deal(int k) {
		//cards[k] = deck.deal();
	//}

	/**
	 * Accesses the deck's size.
	 * @return the number of undealt cards left in the deck.
	 */
	public int deckSize() {
		return deck.size();
	}

	/**
	 * Accesses a card on the board.
	 * @return the card at position k on the board.
	 * @param k is the board position of the card to return.
	 */
	public Card cardAt(int k) {
		return cards[k];
	}


	/**
	 * Generates and returns a string representation of this board.
	 * @return the string version of this board.
	 */
	public String toString() {
		String s = "";
		for (int k = 0; k < cards.length; k++) {
			s = s + k + ": " + cards[k] + "\n";
		}
		return s;
	}

	/**
	 * Deal cards to this board to start the game.
	 */
	private void dealMyCards() {
		deck.deal();
		//System.out.println(""+ deck.deal());
		}
}
