package dev.rbansal.ropephysics;

import com.badlogic.gdx.math.Vector2;

public class VectorTools {

    public static Vector2 divide(Vector2 vec, float scalar) {
        Vector2 result = vec.cpy();
        result.x /= scalar;
        result.y /= scalar;

        return result;
    }

    public static Vector2 mult(Vector2 vec, float scalar) {
        Vector2 result = vec.cpy();
        result.x *= scalar;
        result.y *= scalar;

        return result;
    }

    public static Vector2 add(Vector2 vec1, Vector2 vec2) {
        Vector2 result = vec1.cpy();
        result.x += vec2.x;
        result.y += vec2.y;

        return result;
    }

    public static Vector2 sub(Vector2 vec1, Vector2 vec2) {
        Vector2 result = vec1.cpy();
        result.x -= vec2.x;
        result.y -= vec2.y;

        return result;
    }
}
