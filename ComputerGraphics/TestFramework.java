import graphics.core.*;
import graphics.geometry.*;
import graphics.material.*;
import graphics.math.*;

public class TestFramework extends Base
{
    public Object3D sceneRoot;
    public Camera   camera;
    public Renderer renderer;
    
    public Mesh box;
    
    public MovementRig rig;
    
    public void initialize()
    {
        sceneRoot = new Object3D();
        camera    = new Camera();
        camera.setPosition( new Vector(0,1,3) );
        
        // floor / ground
        Geometry geo = new RectangleGeometry(10, 10);
        Material mat = new SurfaceMaterial();
        Mesh rectangle = new Mesh(geo, mat);
        rectangle.setPosition( new Vector(0, 0, -2) );  
        rectangle.rotateXLocal( -Math.PI/2 );
        sceneRoot.add( rectangle );
        
        /*
        Geometry geo2 = new RectangleGeometry(1, 0.75);
        Material mat2 = new SurfaceMaterial();

        // turn off vertex colors
        // mat2.uniforms.get("useVertexColor").data = 0;
        mat2.uniforms.get("color").data = new float[] {0.5f, 0.5f, 1.0f};
        
        Mesh rectangle2 = new Mesh(geo2, mat2);
        rectangle2.setPosition( new Vector(0, 1, -3) );
        sceneRoot.add( rectangle2 );
        */
       
        /*
        Geometry boxGeo = new BoxGeometry(1,1,1);
        Material boxMat = new SurfaceMaterial();
        box = new Mesh(boxGeo, boxMat);
        box.setPosition( new Vector(0,1.5,0) );
        sceneRoot.add(box);
        */
        
        // Geometry polyGeo = new PolygonGeometry(8);
        // Geometry plane = new PlaneGeometry();
        // Material polyMat = new SurfaceMaterial();
        
        // turn off vertex colors:
        // polyMat.uniforms.get("useVertexColor").data = 0;
        // polyMat.uniforms.get("color").data = new float[] {1, 1, 0};
        
        Geometry sphereGeo = new TorusGeometry();
        Material sphereMat = new SurfaceMaterial();
        
        Mesh mesh = new Mesh(sphereGeo, sphereMat);
        
        // change size
        // poly.scale(2);
        
        mesh.setPosition( new Vector(0, 1.5, 0) );
        sceneRoot.add( mesh );
       
        renderer = new Renderer();
        
        rig = new MovementRig(camera, input);
    }
    
    public void update()
    {
        /*
        box.rotateXLocal(0.011);
        box.rotateYLocal(0.027);
        */
       
        renderer.render( sceneRoot, camera );
        
        rig.update(1.0 / 60.0);
    }
    
    public static void main(String[] args)
    {
        new TestFramework().run();
    }
}
