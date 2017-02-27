/*
 * libsvg-android, ANDROID version
 * Copyright (C) 2010 by Anton Persson
 *
 * http://www.733kru.org/
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of
 * the GNU General Public License version 3; see COPYING for the complete License.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program;
 * if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 */

package me.xiaoluo.svg;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

class HelloSvgCanvas extends View {
    private int width, height;

    private long svgId;

    // We must first load the svgandroid native library

    public HelloSvgCanvas(Context context) {
        super(context);

        InputStream is;
        is = this.getResources().openRawResource(R.raw.hellosvg);

        try {
            StringBuilder sb = new StringBuilder();
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            svgId = SvgRaster.svgAndroidCreate();
            SvgRaster.svgAndroidParseBuffer(svgId, sb.toString());
            SvgRaster.svgAndroidSetAntialiasing(svgId, true);
        } catch (java.io.UnsupportedEncodingException e) {
        } catch (java.io.IOException e) {
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        // get visible area
        width = right - left;
        height = bottom - top;
    }

    @Override
    public void onDraw(Canvas canvas) {
        // paint a white background...
        canvas.drawARGB(255, 255, 255, 255);
        // OK, rasterize the SVG data
		SvgRaster.svgAndroidRender(svgId, canvas);
//        int k;
//        for (k = 1; k < 6; k++) {
//            SvgRaster.svgAndroidRenderToArea(
//                    svgId, canvas,
//                    k * 30, k * 30, 80 * k, 80 * k);
//        }
    }
}

