//// Project 9 Final in Classs
////ANTHONY MERCURIO
String author= "Anthony Mercurio";
int many=5;
Squid school[]=  new Squid[many];
String names[]= { 
  "1", "2", "3", "4", "5"
};
float spacing;

int Mercurio=5;
Lobster avm[]= new Lobster[Mercurio];

int amount=4;
Boat fleet[]=  new Boat[amount];
String uss[]= { 
  "Gillings", "Robert", "J", "Tech Skill", "Hello"
};

float surface;
float moonX=0, moonY=100;
int score=0;

//// SETUP:  size & reset.
void setup() {
  size( 800, 600 );
  spacing=  width/(many+1);
  reset();
}
// Constuct squid(s).
void reset() {
  surface=  random(  height/4, height/2 );
  moonY=  surface/3;
  moonY=  random( 200, surface+200 );
  // Many squids.
  float x=  spacing;
  for (int i=0; i<many; i++ ) {
    school[i]=  new Squid( names[i], x );
    x += spacing;
  }
  for (int i=0; i<amount; i++ ) {
    fleet[i]= new Boat( uss[i] );
  }
  for (int i=0; i<Mercurio; i++) {
    avm[i] = new Lobster();
  }
}


//// NEXT FRAME:  scene, action
void draw() {
  scene();
  if (key == 'B') {
    boatReport( 50, fleet, fleet.length );
    fishReport( surface+50, school, school.length);
  } else action();
  show();
  messages();
  triangles();
}
void messages() {
  fill(0);
  textSize(12);
  text( author, 50, height-50);
  if (score>0) text( "SCORE:  "+score, width*3/4, 20 );
  if (score>900) {
    if (key == 'q') score=0;
    text( "Maximum score.\nQUITTING NOW\nPress the 'q' key to continue", width/2, 60 );
    if (score>10000) { 
      exit();
    }
  }
}

////Drawing triangles on the right side of the screen.
void triangles () {
  for (float y=surface+50; y<height; y+=50) {
    fill(255, 0, 0);
    triangle(width-75, y, width-25, y+25, width-25, y-25);
  }
}

//// METHODS TO MOVE & DRAW.
void scene() {
  background( 50, 150, 200 );      // Dark sky.
  // Moon
  if (moonX > width+100) {
    moonX=  -100;
    moonY=  random( 100, surface+50 );
  }
  moonX += 1;
  noStroke();
  fill( 255,255,255 );
  ellipse( moonX, moonY-150*sin( PI * moonX/width ), 40, 40 );
  // Dark water.
  fill( 0, 100, 50 );
  rect( 0, surface, width, height-surface );
}
void action() {
  // Move squids & boats.
  for (int i=0; i<many; i++ ) {
    school[i].move();
  }
}
//// Display the squids and boats
void show() {
  //squids
  float x=  spacing;
  for (int i=0; i<many; i++ ) {
    school[i].x=  x;
    x += spacing;
    school[i].show();
  }
  //boats
  for ( int i=0; i<amount; i++ ) {
    fleet[i].show();
    fleet[i].move();
  }
  //lobsters
  for ( int i=0; i<Mercurio; i++ ) {
    avm[i].show();
    avm[i].move();
  }
}

//// SUMMARIES:  List all objects in the array.
// Display the properties of each object in the array.
void boatReport( float top, Boat[] b, int many ) {
  fill(255, 200, 200);
  rect( 50, top, width-100, 50 + 20*many );
  float x=70, y=top+20;
  // Labels.
  fill(150, 0, 0);
  text( "BOAT", x+20, y );
  text( "cargo", x+70, y );
  text( "x", x+105, y );
  text( "dx", x+205, y );
  fill(0);
  //
  for (int i=0; i<amount; i++) {
    y += 15;
    text( 1, x, y );
    text( fleet[i].name, x+20, y );
    text( fleet[i].cargo, x+70, y );
    text( fleet[i].x, x+100, y );
    text( fleet[i].dx, x+200, y );
  }
}
void fishReport( float top, Squid[] a, int many ) {
  fill(255, 255, 200);
  rect( 50, top, width-100, 50 + 20*many );
  float x=70, y=top+20;
  // Labels.
  fill(150, 0, 0);
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


//// EVENT HANDLERS:  keys, clicks ////
void keyPressed() {
  if (key == 'r') reset();
  // Send a squid to the bottom!
  if (key == '0') school[0].bottom(); 
  if (key == '1') school[1].bottom(); 
  if (key == '2') school[2].bottom(); 
  if (key == '3') school[3].bottom(); 
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
    }
  }
  if (key == 't') {
    for (int k=0; k<many; k++ ) {
      school[k].y=  surface+10;
      school[k].dy=  -0.1  ;
    }
  }
}




//// OBJECTS ////

class Squid {
  float x, y;        // Coordinates
  float dx=0, dy=0;  // Speed
  float w=40, h=40;
  int legs=10;      // Number of legs.
  String name="";
  float r, g, b;      // Color.
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
    } else if (y<surface) { 
      dy=  -3 * dy;        // Descend fast!
    }
    //Sends squids to bottom if hit by lobster
    for (int i=0; i<Mercurio; i++ ) {
      for (int k=0; k<many; k++ ) {
        if ( avm[i].hit( school[k] ) ) {
          school[k].bottom();
        }
      }
    }
  }
  //// Display the creature.
  void show() {
    fill(r, g, b);
    stroke(r, g, b);
    ellipse( x, y, w, h );         // round top
    rect( x-w/2, y, w, h/2 );      // flat bottom
    fill(255);
    float blink=10;
    if ( y%100 > 80) blink=2;
    ellipse( x, y-h/4, 10, blink );     // eye
    // Legs
    fill(r, g, b);                 // legs.
    float legX=  x-w/2, foot=0;
    foot=  dy>=0 ? 0 : (y%47 > 23) ? 5 : -5;
    strokeWeight(3);
    for (int i=0; i<legs; i++) {
      line( legX, y+h/2, legX+foot, 20+y+h/2 );
      legX += w / (legs-1);
    }
    strokeWeight(3);
    fill(200, 200, 0);
    text( name, x-w/2, y-10+h/2 );
    fill(0);
    text( legs, x+2-w/2, y+h/2 );
    fill(255);
  }
  //// Return true if near
  boolean hit( float xx, float yy ) {
    return dist( xx, yy, x, y ) < h;
  }
}


class Boat {
  String name="";
  float x=0, y=surface, dx=5;
  int cargo=0, caught=0;
  ////Constructor////
  Boat (String name) {
    this.name=name;
  }
  void move() {
    //// Fish before move:  check each squid.
    int caught=0;
    for (int i=0; i<many; i++ ) {
      for ( int k=0; k<amount; k++) {
        if (school[i].hit( fleet[k].x, surface )) {
          caught += school[i].legs;
        }
      }
    }
    cargo += caught;    
    //// Now, move the boat.
    x += dx;
    if (caught>0 && dx>0 ) this.x += 20;      //  Jump after catch.
    if (caught>0 && dx<0 ) this.x -= 20;      //  Jump after catch.
    if (x<0) {
      score += cargo;            // Add cargo to global score.
      cargo=0;
      dx = random( 1, 5 );      // Variable boat speed.
    }
    if (x>width) {
      dx = -random( 0.5, 3 );    // Slower return.
    }
  }
  //// Draw the boat.
  void show() {
    // Boat.
    fill(255, 0, 0);
    noStroke();
    rect( x, surface-20, 50, 20 );
    if (dx>0)   triangle( x+50, surface, x+50, surface-20, x+70, surface-20 );
    else        triangle( x, surface, x, surface-20, x-20, surface-20 );
    rect( x+12, surface-30, 25, 10 );      // Cabin.
    fill(0);
    rect( x+20, surface-40, 10, 10 );      // Smokestack.
    // Display name & cargo.
    fill(255);
    text( name, x+5, surface-10 );
    fill(0);
    if (cargo>0) text( cargo, x+20, surface );
    // Smoke
    fill( 50, 50, 50, 200 );
    ellipse( x +20 -10*dx, surface-50, 20, 20 );
    ellipse( x +20 -20*dx, surface-60, 15, 10 );
    ellipse( x +20 -30*dx, surface-70, 8, 5 );
  }
}


////LOBSTER CLASS////
class Lobster {
  //PROPERTIES//
  float x, y, dx, dy;
  float clawY=5;
  int r, g, b;
  boolean clawClosed=false;

  //CONSTRUCTORS//
  Lobster () {
    this.x=0;
    this.y=random((surface+height)/2, height);
    this.dx=random(0, 3);
    this.dy=random(-1, 0);
  }

  //METHODS//
  //draws lobster
  void show() {
    fill(255, 0, 0);
    ellipse(x, y, 40, 20);   //Body
    if (dx>0) {
      strokeWeight(20);      //Right Side Claws
      line(x+15, y-5, x+25, y-clawY);
      line(x+15, y+5, x+25, y+clawY);
      strokeWeight(5);
    } else {
      strokeWeight(20);      //Left Side Claws
      line(x-15, y-5, x-25, y-clawY);
      line(x+15, y+5, x+25, y+clawY);
      strokeWeight(5);
    }
    //cycles open-closed on claws
    clawClosed= int(frameCount/30) %2 >0;
    if (clawClosed) clawY=0;
    else            clawY=5;
  }

  //Make lobster move
  void move() {
    x+=dx; 
    y+=dy;
    if (x<0 || x>width) dx*=-1;
    if (y<surface || y>height)dy*=-1;
  }

  //hit method for squids
  boolean hit (Squid p) {
    if (dist(p.x, p.y, this.x, this.y)<30) {
      return true;
    } else return false;
  }
}
