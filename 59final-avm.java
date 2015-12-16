//Project 9 in class final exam revision
//Anthony Mercurio

int many=5;
Squid school[]=  new Squid[many];
String names[]=  { "Robert", "John", "Gillings", "Final", "A" };
float spacing;



int Mercurio=5;
Boat ships[]= new Boat[Mercurio];
String boatnames[]=  { "1", "2", "3", "4", "5" };
//for final exam pt 2
int Lobster=5;
Lobsters[] avm= new Lobster[Lobster];
String lobsternames[]= {"harry", "rodney", "jacob", "smith", "ursula"};

//Boat bounty=  new Boat();

float surface;
float moonX=0, moonY=100;
float creatureX=0, creatureY=200;
float redX=0, redY=100, redZ=200;
int score=0;

//// SETUP:  size & reset.
void setup() {
  size( 800, 600 );
  spacing=  width/(many+1);
  reset();
  help();
  triangles();
  
 
  
 
  for ( int i=0; i<Mercurio; i++){
    ships[i]= new Boat(int (random(0,width)), boatnames[i]);
  }
  
  
}



//construct lobsters
void reset() {
  surface=  random(  height, height );
  creatureY=  surface;
  creatureY=  random( 300, surface );
  // Many Lobsters
  float x=  spacing;
  for (int i=0; i<many; i++ ) {
    school[i]=  new Lobster( names[i], x );
    x += spacing;
   

  }
  //bounty.name=  "Bounty";
}

////Drawing triangles on the right side of the screen.
void triangles () {
  for (float y=surface+50; y<height; y+=50) {
    fill(255, 0, 0);
    triangle(width-75, y, width-25, y+25, width-25, y-25);
  }
}

// Constuct squid(s).
void reset() {
  surface=  random(  height/2, height/2 );
  moonY=  surface;
  moonY=  random( 300, surface );
  // Many squids.
  float x=  spacing;
  for (int i=0; i<many; i++ ) {
    school[i]=  new Squid( names[i], x );
    x += spacing;
   

  }
  //bounty.name=  "Bounty";
}

void help() {
  background(0);
  textSize(25);
  fill(255,0,0);
  text( "Press 0 key to send 1st squid to the bottom.", 150, 50);
  text( "Press 1 key to send 2nd squid to the bottom.", 150, 80);
  text( "Press 2 key to send 3rd squid to the bottom.", 150, 110);
  text( "Press 3 key to send 4th squid to the bottom.", 150, 140);
  text( "Press 4 key to send 5th squid to the bottom.", 150, 170);
  fill(128,0,0);
  text( "Cheat Codes----------", 50,200);
  text( "Press h key to send highest squid to the bottom.", 150,230 );
  text( "Press b key to send all squids to the bottom.", 150, 260);
  text( "Press t key to send all squids to the surface.", 150, 290);
  fill(0,255,255);
  text( "Sorting", 50,320); 
  text( "X key sorts the squids in order of position (x).", 150,350);
  text( "Y key sorts the squids in order of height (y).", 150,380);
  text( "S key sorts the squids in order of speed (dy).", 150,410);
  text( "L key sorts the squids in order of legs.", 150,440);
  text( "B key sorts the boats in order of position (x).", 150,470);
  text( "D key sorts the boats in order of speed (dx).", 150,500);
  text( "F key sorts the boats in order of greatest cargo.", 150,530);
  fill(255);
  text( "Press any capital letter to see Statistics.", 150,560);
  text( "Press any key to exit." , 150,590); 
  
  
}

//// NEXT FRAME:  scene, action
void draw() {
  scene();
  show();
  showlobster();


   if (key == 'v') {
    help();
  }
  if (key >= 'A' && key <= 'Z') {
    boatReport( 50, ships, ships.length );
    fishReport( surface+50, school, school.length);
  }
  else action();
  messages();
}
void messages() {
  fill(255);
  textSize( 20 );
  text( "Squid School :)", width/3, 20 );
  textSize(12);
  text( "Hold B key to show all boats and fish", width/3, 40 );
  text( "Press v key for help", width/3, 60 );
  text( "Project 9 Anthony Mercurio", 600, 20 );
  if (score>0) text( "SCORE:  "+score, width*3/4,100 );
  if (score>900) {
    if (key == 'q') score=0;
    text( "\nPress the 'q' key to continue", width/2, 60 );
    if (score>10000) { exit(); }
  }
}

//// METHODS TO MOVE & DRAW.
void scene() {
  background( 0 );      // night sky
  // Moon
  if (moonX > width+100) {
    moonX=  -100;
    moonY=  random( 100, surface+50 );
  }
  moonX += 1;
  fill( 255 );
  ellipse( moonX, moonY-150*sin( PI * moonX/width ), 100,100 );

  // WATER
  fill( 0,0,255 );
  noStroke();
  rect( 0,surface, width, height-surface ); 
 
  

 
  }




void action() {
  // Move squids & boats.
  for (int i=0; i<many; i++ ) {
    school[i].move();
  }
  for (int i=0; i<Mercurio; i++ ) {
    ships[i].move();
  }
  for (int i=0; i<Lobster; i++ ) {
    ships[i].move();
  }
  
}
//// Display the squids in (sorted) order.
void show() {
  float x=  spacing;
  for (int i=0; i<many; i++ ) {
    school[i].x=  x;
    x += spacing;
    school[i].show();
  }
  
  for (int i=0; i<Mercurio; i++ ) {
    ships[i].show();

  }
}
////display lobsters
void showlobsters() {
  float x=  spacing;
  for (int i=0; i<many; i++ ) {
    school[i].x=  x;
    x += spacing;
    school[i].show();
  }
  
  for (int i=0; i<Lobster; i++ ) {
    ships[i].show();

  }
}

//// SUMMARIES:  List all objects in the array.
// Display the properties of each object in the array.
void boatReport( float top, Boat[] b, int many ) {
  fill(255,200,200);
  rect( 50,top, width-100, 50 + 20*many );
  float x=70, y=top+20;
  // Labels.
  fill(0);
  text( "BOAT", x+20, y );
  text( "cargo", x+70, y );
  text( "x", x+105, y );
  text( "dx", x+205, y );
  fill(0);
  //
   for (int i=0; i<many; i++) {
    y += 15;    
    text( i, x, y );
    text( b[i].name, x+20, y );
    text( b[i].cargo, x+70, y );
    text( b[i].x, x+100, y );
    text( b[i].dx, x+200, y );
  }
}
void fishReport( float top, Squid[] a, int many ) {
  fill(255,255,200);
  rect( 50,top, width-100, 50 + 20*many );
  float x=70, y=top+20;
  // Labels.
  fill(150,0,0);
  text( "FISH", x+20, y );
  text( "legs", x+70, y );
  text( "x", x+105, y );
  text( "y", x+205, y );
  text( "dy", x+305, y );
  fill(0);
  for (int i=0; i<many; i++) {
    y += 15;    // Next line.
    text( i, x, y );
    text( a[i].name, x+20, y );
    text( a[i].legs, x+70, y );
    text( a[i].x, x+100, y );
    text( a[i].y, x+200, y );
    text( a[i].dy, x+300, y );
  }
}
    void lobsterReport( float top, Lobster[] a, int many ) {
  fill (255);
  ellipse(width/2,height);
  // Labels.
  fill(150,0,0);
  text( "Lobster", x+20, y );
  text( "legs", x+70, y );
  text( "x", x+105, y );
  text( "y", x+205, y );
  text( "dy", x+305, y );
  fill(0);
  for (int i=0; i<many; i++) {
    y += 15;    // Next line.
    text( i, x, y );
    text( a[i].name, x+20, y );
    text( a[i].legs, x+70, y );
    text( a[i].x, x+100, y );
    text( a[i].y, x+200, y );
    text( a[i].dy, x+300, y );
  }
}

//// EVENT HANDLERS:  keys, clicks ////
void keyPressed() { 
  //sorting for squid.
  if(key == 'L') sortSquidLeg(school,many);
  if(key == 'Y') sortSquidPosY(school, many);
  if(key == 'S') sortSquidDY(school, many);
  if(key == 'X') sortSquidPosX(school, many);
  //sorting for boat.
  if(key == 'B') sortBoatPosX( ships, many);
  if(key == 'D') sortBoatDX( ships, many);
  if(key == 'F') sortBoatcargo( ships, many);
  //reset
  if (key == 'r') reset();
  // Send a squid to the bottom!
  if (key == '0') school[0].bottom(); 
  if (key == '1') school[1].bottom(); 
  if (key == '2') school[2].bottom(); 
  if (key == '3') school[3].bottom();
  if (key == '4') school[4].bottom();
  //// Send highest to bottom.
  if (key == 'h') {
    int k=0;
    for (int i=1; i<many; i++ ) {
      if (school[i].y < school[k].y) k=i;           // k is index of highert.
    }
    school[k].bottom();     
  }
  
  
  // Cheat codes:
  //// Send all to top or bottom.
  if (key == 'b') {
    for (int k=0; k<many; k++ ) {
      school[k].bottom();
      ships[k].surface();
    }
  }
  if (key == 't') {
    for (int k=0; k<many; k++ ) {
      school[k].y=  surface+10;
      school[k].dy=  -0.1  ;
    }
  } 
}

 
//Sorting the squids
void sortSquidLeg( Squid[] a, int many) {
      //make the array smaller
      for (int m=many; m>1; m--) {
      int k=0;
      for( int j=1; j<m; j++){
        if (a[j].legs>a[k].legs) 
        k=j;
     }
    // k  points to the biggest
    swap( a, m-1, k);
    }
   
}
void sortSquidPosY( Squid[] a, int many) {
      //array becomes smaller
      for (int m=many; m>1; m--) {
      int k=0;
      for( int j=1; j<m; j++){
        if (a[j].y>a[k].y) 
        k=j;
     }
    // k points to the biggest
    swap( a, m-1, k);
    }
   
}

void sortSquidDY( Squid[] a, int many) {
      //smaller array
      for (int m=many; m>1; m--) {
      int k=0;
      for( int j=1; j<m; j++){
        if (a[j].dy>a[k].dy) 
        k=j;
     }
    // k shows biggest
    swap( a, m-1, k);
    }
   
}
void sortSquidPosX( Squid[] a, int many) {
      //array smaller
      for (int m=many; m>1; m--) {
      int k=0;
      for( int j=1; j<m; j++){
        if (a[j].x>a[k].x) 
        k=j;
     }
  swap( a, m-1, k);
    }
}
void swap(Squid[] a, int j, int k){
  int tmp;
  tmp=  a[j].legs;
  a[j].legs=  a[k].legs;
  a[k].legs=  tmp;
  
  float temp;
  temp=  a[j].y;
  a[j].y=  a[k].y;
  a[k].y=  temp;
  
  temp=  a[j].dy;
  a[j].dy=  a[k].dy;
  a[k].dy=  temp;
  
  temp=  a[j].x;
  a[j].x=  a[k].x;
  a[k].x=  temp;
  
}


///Sorting the boat
void sortBoatPosX( Boat[] a, int many) {
      //shrink the array.
      for (int m=many; m>1; m--) {
      int k=0;
      for( int j=1; j<m; j++){
        if (a[j].x>a[k].x) 
        k=j;
     }
    swap( a, m-1, k);
    }    
}
void sortBoatDX( Boat[] a, int many) {
      for (int m=many; m>1; m--) {
      int k=0;
      for( int j=1; j<m; j++){
        if (a[j].dx>a[k].dx) 
        k=j;
     }
      swap( a, m-1, k);
    }    
}

void sortBoatcargo( Boat[] a, int many) {
      for (int m=many; m>1; m--) {
      int k=0;
      for( int j=1; j<m; j++){
        if (a[j].cargo>a[k].cargo) 
        k=j;
     }
    swap( a, m-1, k);
    }    
}

void swap(Boat[] a, int j, int k){
  
   int tmp;
  tmp=  a[j].x;
  a[j].x=  a[k].x;
  a[k].x=  tmp;
 
  float temp;
  temp=  a[j].dx;
  a[j].dx=  a[k].dx;
  a[k].dx=  temp;
  
  tmp=  a[j].cargo;
  a[j].cargo=  a[k].cargo;
  a[k].cargo=  tmp;

}

//// OBJECTS ////

///final exam lobster

class Lobster {
  float x,y;
  float dx=0,dy=0;
  float w=80, h=70;
  int legs=2
  String name="";
  float r,g,b  //Color.
  int count=0;
}
//// CONSTRUCTORS ////
  Lobster( String s, float x ) {
    this.name=  s;
    this.x=x;
    bottom();
    // RED
    r=(255);
    g=(255);
    b=(255);
  }
class Squid {
  float x,y;        // Coordinates
  float dx=0,dy=0;  // Speed
  float w=40,h=40;
  int legs=10;      // Number of legs.
  String name="";
  float r,g,b;      // Color.
  int count=0;
  //// CONSTRUCTORS ////
  Squid( String s, float x ) {
    this.name=  s;
    this.x=x;
    bottom();
    // Purplish
    r=  random(100, 255);
    g=  random(0, 100);
    b=  random(100, 250);
  }
  //// Start again at bottom.  (Random speed.)
  void bottom() {
    y=  height - h;
    dy=  -random( 0.1, 0.9 );
    legs=  int( random(1, 10.9) );
  }
  //// METHODS:  move & show ////
  void move() {
    x += dx;
    y += dy;
    if (y>height) { 
      bottom();
      count++;
    }
    else if (y<surface) { 
      dy=  -3 * dy;        // Descend fast!
    }
  }
   //Make lobster move
  void move() {
    x+=dx; 
    y+=dy;
    if (x<0 || x>width) dx*=-1;
    if (y<surface || y>height)dy*=-1;
  }
  
  //// Display the creature.
  void show() {
    fill(r,g,b);
    stroke(r,g,b);
    ellipse( x,y, w,h );         // round top
    rect( x-w/2,y, w,h/2 );      // flat bottom
    fill(255);
    float blink=10;
    if ( y%100 > 80) blink=2;
    ellipse( x,y-h/4, 10, blink );     // eye
    // Legs
    fill(r,g,b);                 // legs.
    float legX=  x-w/2, foot=0;
    foot=  dy>=0 ? 0 : (y%47 > 23) ? 5 : -5;
    strokeWeight(3);
    for (int i=0; i<legs; i++) {
      line( legX, y+h/2, legX+foot, 20+y+h/2 );
      legX += w / (legs-1);
    }
      strokeWeight(3);
    fill(200,200,0);
    text( name, x-w/2, y-10+h/2 );
    fill(0);
    text( legs, x+2-w/2, y+h/2 );
    fill(255);
    if (count>0) text( count, x, y+h/2 );
  }
  //// Return true if near
  boolean hit( float xx, float yy ) {
    return dist( xx,yy, x,y ) < h;
  }
}


class Boat {
  String name="";
  float y=surface, dx=5;
  int x=0;
  int cargo=0, caught=0;
  float r=random(0,255), g=random(0,255), b=random(0,255);
  
  
  Boat( int tempx, String temps) {
    x=tempx;
    name=temps;
  }
  void move() {
    //// Fish before move:  check each squid.
    int caught=0;
    for (int i=0; i<many; i++ ) {
    if (school[i].hit( x, surface )) {
        caught += school[i].legs;
        school[i].bottom();     
      }
     
    }
    cargo += caught;    
    //// Now, move the boat.
    x += dx;
    if (caught>0) x += 2*dx;      //  Jump after catch.
    if (x<90) {
      score += cargo;            // Add cargo to global score.
      cargo=0;
      dx = random( 1, 5 );      // Variable boat speed.
    }
    if (x>width)  {
      dx = -random( 0.5, 3 );    // Slower return.
    }
  }
  //// Draw the boat.
   void surface() {
     x= int( random(1,100));
   }
  
 
  void show() {
    // Boat.
    fill(0,255,127);
    noStroke();
    rect( x, surface-20, 50, 20 );
    if (dx>0)   triangle( x+50,surface, x+50,surface-20, x+70,surface-20 );
    else        triangle( x,surface, x,surface-20, x-20,surface-20 );
    rect( x+12, surface-30, 25, 10 );      // Cabin.
    fill(255,0,0);
    rect( x+20, surface-40, 10, 10 );      // Smokestack.
    // Display name & cargo.
    fill(255);
    text( name, x+5, surface-10 );
    fill(0);
    if (cargo>0) text( cargo, x+20, surface );
    // Smoke
    int k= frameCount/30%2;
    if (k==0) {
    fill( 50,50,50, 200 );
    ellipse( x +20 -10*dx, surface-50, 20, 20 );
    ellipse( x +20 -20*dx, surface-60, 15, 10 );
    ellipse( x +20 -30*dx, surface-70, 8, 5 );
   }   
   else{
   } 
 }

}
