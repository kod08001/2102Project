

	
	package Project2102;

	import org.jhotdraw.util.*;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.*;
import javax.swing.*;
import org.jhotdraw.app.*;
import org.jhotdraw.draw.*;
import org.jhotdraw.draw.action.*;
import org.jhotdraw.samples.draw.*;

import test2102.TestWindow;
import static org.jhotdraw.draw.AttributeKeys.*;

	public class Main extends DrawApplicationModel {

		public Main(){
		super();		
		}
		
		private DefaultDrawingEditor getEditor(Project pr){
			DrawProject p = (DrawProject) pr;
		    
		    if (p == null) {
		        return getSharedEditor();
		    } else {
		        return (DefaultDrawingEditor) p.getEditor();
		    }
		}
		
		@Override
		public List<JToolBar> createToolBars(Application a, Project pr) {
			DefaultDrawingEditor editor = getEditor(pr);
			
			ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.draw.Labels");
			
			List<JToolBar> toolBars = new LinkedList<JToolBar>();
			JToolBar newBar = new JToolBar();
			
		
			super.addDefaultCreationButtonsTo(newBar, editor,
					ToolBarButtonFactory.createDrawingActions(editor), 
	                ToolBarButtonFactory.createSelectionActions(editor)
	                );
			
			Map<AttributeKey,Object> attributeMap = new HashMap<AttributeKey, Object>();
			attributeMap.put(AttributeKeys.FILL_COLOR, Color.RED);
			attributeMap.put(AttributeKeys.STROKE_COLOR, Color.BLUE);
			
			RectangleFigure classname = new RectangleFigure(0, 0, 15, 10);
			RectangleFigure attributes = new RectangleFigure(0, 10, 15, 20);
			RectangleFigure methods = new RectangleFigure(0, 30, 15, 20);
			GroupFigure classfigure = new GroupFigure();
			classfigure.add(classname);
			classfigure.add(attributes);
			classfigure.add(methods);
	                
//			ToolBarButtonFactory.addStrokeButtonsTo(newBar, super.getSharedEditor());
//			ToolBarButtonFactory.addToolTo(newBar, editor, new CreationTool(new ClassFigure()), "createRectangle", labels);
			ToolBarButtonFactory.addToolTo(newBar, editor, new CreationTool(classfigure), "createRectangle", labels);
			
			
			
			 
			//put your own button into newBar here. Perhaps you need to extend
			//ToolBarButtonFactory with your own class to make a factory that produces
			//custom buttons? Or do you want to add all the code to create and register
			//a button here? 
			toolBars.add(newBar);
			return toolBars;
		}
		//How does the toolbar button factory work? Open it up in the draw.action package.
		//show the addStrokeWidthButtonTo method. 
		// It first creates a button object, then adds an AttributeAction to the button. 
		// This action defines the functionality of the button.
		// There are a bunch of actions defined in the draw.action package. 
		
		//Go back to DrawApplicationModel.java and look at the addDefaultCreationButtonsTo class. 
		//  Here is where you create buttons for establishing Tools: a 'mode of drawing'. So if you want
		//  to free draw, you need a tool for that; if you want to create a rectangle, you need to activate
		//  a tool for that too. 

		public static void main(String[] args){
			//Application app = new DefaultOSXApplication();
			Application app = new AppWindow();
			
			DrawApplicationModel myProject = new Main();
			app.setModel(myProject);
			myProject.setName("Test");
			myProject.setProjectClassName("org.jhotdraw.samples.draw.DrawProject");
			app.launch(args);
		
		
		}
		
	}


