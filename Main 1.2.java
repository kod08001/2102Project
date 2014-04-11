

	
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
			
			RectangleFigure classSpace = new RectangleFigure(0, 0, 15, 10);
			TextFigure classText = new TextFigure("Class name");
			GroupFigure className = new GroupFigure();
			className.add(classSpace);
			className.add(classText);
			
			RectangleFigure attributeSpace = new RectangleFigure(0, 10, 15, 20);
			TextFigure attributeText = new TextFigure("Attributes");
			GroupFigure attributes = new GroupFigure();
			attributes.add(attributeSpace);
			attributes.add(attributeText);
			
			RectangleFigure methodSpace = new RectangleFigure(0, 30, 15, 20);
			TextFigure methodText = new TextFigure("Methods");
			GroupFigure methods = new GroupFigure();
			methods.add(methodText);
			methods.add(methodSpace);
			
			GroupFigure classFigure = new GroupFigure();
			classFigure.add(className);
			classFigure.add(attributes);
			classFigure.add(methods);
	                
//			ToolBarButtonFactory.addStrokeButtonsTo(newBar, super.getSharedEditor());

			ToolBarButtonFactory.addToolTo(newBar, editor, new CreationTool(classFigure), "createRectangle", labels);
			
			toolBars.add(newBar);
			return toolBars;
		}
		

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


