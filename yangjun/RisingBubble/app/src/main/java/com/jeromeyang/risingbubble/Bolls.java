package com.jeromeyang.risingbubble;

/**
 * Created by Jeromeyang on 16/6/1.
 */
public class Bolls {
        private float cx;
        private float cy;
        private float radius;
        private int addx = 0;
        private int addy =4;
        private int addRadius;
        MySurfaceView view;

        public Bolls(MySurfaceView view ,int cx, int cy){
            this.view=view;
            this.cx = cx;
            this.cy =cy;
            radius=(float) 30;
            addRadius=(int) (Math.random()*10);
        }

        public float getCx() {
            return cx;
        }
        public void setCx(float cx) {
            this.cx = cx;
        }
        public float getCy() {
            return cy;
        }
        public void setCy(float cy) {
            this.cy = cy;
        }
        public float getRadius() {
            return radius;
        }
        public void setRadius(float radius) {
            this.radius = radius;
        }
        public void draw(){
           addy =  addy+1;
            cx += addx;
            cy -= addy;
//            radius+=addRadius;
//            if (cx < radius) {
//                addx = Math.abs(addx);
//            }
//            if (cx > view.getWidth() - radius) {
//                addx = -Math.abs(addx);
//            }
//            if (cy < radius) {
//                addy = Math.abs(addy);
//            }
//            if (cy > view.getHeight() -radius) {
//                addy = -Math.abs(addy);
//            }
        }

}
