package Manager;


import Bean.Coordinates;
import Bean.Shape;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.accept.MediaTypeFileExtensionResolver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Controller;

import javax.print.attribute.standard.Media;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class MainController {
    public String figures;
    public LayerManager layerManager = new LayerManager();


    @GetMapping("/circle")
    public String addCircle(
            @RequestParam(name = "xCoordinate", required = false, defaultValue = "10") String xCoordinate,
            @RequestParam(name="yCoordinate", required = false, defaultValue="10") String yCoordinate,
            @RequestParam(name = "r", required = false, defaultValue = "10") String r,
            @RequestParam(name = "color", required = false, defaultValue = "yellow") String color, Model model) {



        List<Coordinates> coordinates = new ArrayList<>();
        try {
            coordinates.add(new Coordinates(Double.parseDouble(xCoordinate), Double.parseDouble(yCoordinate)));
            layerManager.getAbstractFactory().createShape("Circle", coordinates, Double.parseDouble(r), color);
            printFigures(model);
            layerManager.updateProxySVG();

        }catch(Exception e){
            String alert = "<script> alert('" + "Invalid Input" +  "') </script>";
            System.out.println(alert);
            model.addAttribute("circleError", alert);
        }

        String i = String.valueOf(layerManager.getChoice());

        chooseLayer(i, model);

        return "editor";
    }

    @GetMapping(value = "/getSVG")
    public void getImage(HttpServletResponse response) throws IOException {
        layerManager.saveSVG();
        FileSystemResource imgFile = new FileSystemResource(System.getProperty("user.dir") + "/myfile.svg");
        System.out.println(imgFile.getURL());
        response.setHeader("Content-Disposition", "attachment; filename=myfile.svg");
        response.setContentType("image/svg-xml");
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
    }





    @GetMapping("/line")
    public String addLine(@RequestParam(name = "x1", required = false, defaultValue = "10") String x1,
                          @RequestParam(name = "y1", required = false, defaultValue = "10") String y1,
                          @RequestParam(name = "x2", required = false, defaultValue = "10") String x2,
                          @RequestParam(name = "y2", required = false, defaultValue = "10") String y2,
                          String color, Model model) {

        List<Coordinates> coordinates = new ArrayList<>();

        try {
            coordinates.add(new Coordinates(Double.parseDouble(x1), Double.parseDouble(y1)));
            coordinates.add(new Coordinates(Double.parseDouble(x2), Double.parseDouble(y2)));
            layerManager.getAbstractFactory().createShape("Line", coordinates, color);
            layerManager.updateProxySVG();
            printFigures(model);
        }catch(Exception e){
            String alert = "<script> alert('" + "Invalid Input" +  "') </script>";
            System.out.println(alert);
            model.addAttribute("lineError", alert);
        }

        String i = String.valueOf(layerManager.getChoice());

        chooseLayer(i, model);

        return "editor";

    }

    @GetMapping("/triangle")
    public String addTriangle(@RequestParam(name = "x1", required = false, defaultValue = "10") String x1,
                              @RequestParam(name = "y1", required = false, defaultValue = "10") String y1,
                              @RequestParam(name = "x2", required = false, defaultValue = "10") String x2,
                              @RequestParam(name = "y2", required = false, defaultValue = "10") String y2,
                              @RequestParam(name = "y3", required = false, defaultValue = "10") String x3,
                              @RequestParam(name = "y3", required = false, defaultValue = "10") String y3,
                              String color, Model model) {

        List<Coordinates> coordinates = new ArrayList<>();
        try {
            coordinates.add(new Coordinates(Double.parseDouble(x1), Double.parseDouble(y1)));
            coordinates.add(new Coordinates(Double.parseDouble(x2), Double.parseDouble(y2)));
            coordinates.add(new Coordinates(Double.parseDouble(x3), Double.parseDouble(y3)));
            layerManager.getAbstractFactory().createShape("triangle", coordinates, color);
            layerManager.updateProxySVG();
            printFigures(model);

        }catch(Exception e){
            String alert = "<script> alert('" + "Invalid Input" +  "') </script>";
            System.out.println(alert);
            model.addAttribute("triangleError", alert);
        }
        String i = String.valueOf(layerManager.getChoice());

        chooseLayer(i, model);

        return "editor";
    }

    @GetMapping("/ellipse")
    public String addEllipse(@RequestParam(name = "x", required = false, defaultValue = "10") String x,
                             @RequestParam(name="y", required=false, defaultValue="10") String y,
                             @RequestParam(name = "rx", required = false, defaultValue = "10") String rx,
                             @RequestParam(name = "ry", required = false, defaultValue = "10") String ry,
                             @RequestParam(name = "color", required = false, defaultValue = "yellow") String color, Model model) {

        List<Coordinates> coordinates = new ArrayList<>();

        try {
            coordinates.add(new Coordinates(Double.parseDouble(x), Double.parseDouble(y)));
            layerManager.getAbstractFactory().createShape("Ellipse",  coordinates, Double.parseDouble(rx), Double.parseDouble(ry), color);
            layerManager.updateProxySVG();
            printFigures(model);

        }catch(Exception e){
            String alert = "<script> alert('" + "Invalid Input" +  "') </script>";
            model.addAttribute("ellipseError", alert);
        }
        String i = String.valueOf(layerManager.getChoice());

        chooseLayer(i, model);

        return "editor";
    }

    @GetMapping("/text")
    public String addText(@RequestParam(name = "x", required = false, defaultValue = "10") String x,
                          @RequestParam(name="y", required=false, defaultValue="10") String y,
                          @RequestParam(name="text", required=false, defaultValue="10") String text,
                          @RequestParam(name = "color", required = false, defaultValue = "yellow") String color, Model model) {

        List<Coordinates> coordinates = new ArrayList<>();
        try {
            coordinates.add(new Coordinates(Double.parseDouble(x), Double.parseDouble(y)));
            layerManager.getAbstractFactory().createShape("Text", coordinates, text, color);
            layerManager.updateProxySVG();
            printFigures(model);

        }catch(Exception e){
            String alert = "<script> alert('" + "Invalid Input" +  "') </script>";
            System.out.println(alert);
            model.addAttribute("textError", alert);
        }
        String i = String.valueOf(layerManager.getChoice());

        chooseLayer(i, model);

        return "editor";
    }

    @GetMapping("/quadrangle")
    public String addQuad(@RequestParam(name = "x", required = false, defaultValue = "10") String x,
                          @RequestParam(name="y", required=false, defaultValue="10") String y,
                          @RequestParam(name="width", required=false, defaultValue="10") String width,
                          @RequestParam(name="height", required=false, defaultValue="10") String height,
                          @RequestParam(name = "color", required = false, defaultValue = "yellow") String color, Model model) {

        List<Coordinates> coordinates = new ArrayList<>();
        try {
            coordinates.add(new Coordinates(Double.parseDouble(x), Double.parseDouble(y)));
            layerManager.getAbstractFactory().createShape("quadrangle", coordinates, Double.parseDouble(width), Double.parseDouble(height), color);
            layerManager.updateProxySVG();

            printFigures(model);

        }catch(Exception e){
            String alert = "<script> alert('" + "Invalid Input" +  "') </script>";
            System.out.println(alert);
            model.addAttribute("quadrangleError", alert);
        }
        String i = String.valueOf(layerManager.getChoice());

        chooseLayer(i, model);
        return "editor";
    }

    @GetMapping("/star")
    public String addStar(@RequestParam(name = "x1", required = false, defaultValue = "10") String x1,
                          @RequestParam(name = "y1", required = false, defaultValue = "10") String y1,
                          @RequestParam(name = "x2", required = false, defaultValue = "10") String x2,
                          @RequestParam(name = "y2", required = false, defaultValue = "10") String y2,
                          @RequestParam(name = "x3", required = false, defaultValue = "10") String x3,
                          @RequestParam(name = "y3", required = false, defaultValue = "10") String y3,
                          @RequestParam(name = "x4", required = false, defaultValue = "10") String x4,
                          @RequestParam(name = "y4", required = false, defaultValue = "10") String y4,
                          @RequestParam(name = "x5", required = false, defaultValue = "10") String x5,
                          @RequestParam(name = "y5", required = false, defaultValue = "10") String y5,
                          String color, Model model) {
        List<Coordinates> coordinates = new ArrayList<>();
        try {
            coordinates.add(new Coordinates(Double.parseDouble(x1), Double.parseDouble(y1)));
            coordinates.add(new Coordinates(Double.parseDouble(x2), Double.parseDouble(y2)));
            coordinates.add(new Coordinates(Double.parseDouble(x3), Double.parseDouble(y3)));
            coordinates.add(new Coordinates(Double.parseDouble(x4), Double.parseDouble(y4)));
            coordinates.add(new Coordinates(Double.parseDouble(x5), Double.parseDouble(y5)));
            layerManager.getAbstractFactory().createShape("star", coordinates, color);
            layerManager.updateProxySVG();

            printFigures(model);

        }catch(Exception e){
            String alert = "<script> alert('" + "Invalid Input" +  "') </script>";
            System.out.println(alert);
            model.addAttribute("starError", alert);
        }
        String i = String.valueOf(layerManager.getChoice());

        chooseLayer(i, model);

        return "editor";
    }

    @GetMapping("/ngon")
    public String addNgon(@RequestParam(name = "x1", required = false, defaultValue = "0") String x1,
                          @RequestParam(name = "y1", required = false, defaultValue = "0") String y1,
                          @RequestParam(name = "x2", required = false, defaultValue = "0") String x2,
                          @RequestParam(name = "y2", required = false, defaultValue = "0") String y2,
                          @RequestParam(name = "x3", required = false, defaultValue = "0") String x3,
                          @RequestParam(name = "y3", required = false, defaultValue = "0") String y3,
                          @RequestParam(name = "x4", required = false, defaultValue = "0") String x4,
                          @RequestParam(name = "y4", required = false, defaultValue = "0") String y4,
                          @RequestParam(name = "x5", required = false, defaultValue = "0") String x5,
                          @RequestParam(name = "y5", required = false, defaultValue = "0") String y5,
                          @RequestParam(name = "x1", required = false, defaultValue = "0") String x6,
                          @RequestParam(name = "y1", required = false, defaultValue = "0") String y6,
                          @RequestParam(name = "x2", required = false, defaultValue = "0") String x7,
                          @RequestParam(name = "y2", required = false, defaultValue = "0") String y7,
                          @RequestParam(name = "x3", required = false, defaultValue = "0") String x8,
                          @RequestParam(name = "y3", required = false, defaultValue = "0") String y8,
                          @RequestParam(name = "x4", required = false, defaultValue = "0") String x9,
                          @RequestParam(name = "y4", required = false, defaultValue = "0") String y9,
                          @RequestParam(name = "x5", required = false, defaultValue = "0") String x10,
                          @RequestParam(name = "y5", required = false, defaultValue = "0") String y10,
                          String color, Model model) {
        List<Coordinates> coordinates = new ArrayList<>();
        try {
            coordinates.add(new Coordinates(Double.parseDouble(x1), Double.parseDouble(y1)));
            coordinates.add(new Coordinates(Double.parseDouble(x2), Double.parseDouble(y2)));
            coordinates.add(new Coordinates(Double.parseDouble(x3), Double.parseDouble(y3)));
            coordinates.add(new Coordinates(Double.parseDouble(x4), Double.parseDouble(y4)));
            coordinates.add(new Coordinates(Double.parseDouble(x5), Double.parseDouble(y5)));
            coordinates.add(new Coordinates(Double.parseDouble(x6), Double.parseDouble(y6)));
            coordinates.add(new Coordinates(Double.parseDouble(x7), Double.parseDouble(y7)));
            coordinates.add(new Coordinates(Double.parseDouble(x8), Double.parseDouble(y8)));
            coordinates.add(new Coordinates(Double.parseDouble(x9), Double.parseDouble(y9)));
            coordinates.add(new Coordinates(Double.parseDouble(x10), Double.parseDouble(y10)));

            layerManager.getAbstractFactory().createShape("ngon", coordinates, color);
            layerManager.updateProxySVG();

            printFigures(model);

        }catch(Exception e){
            String alert = "<script> alert('" + "Invalid Input" +  "') </script>";
            System.out.println(alert);
            model.addAttribute("ngonError", alert);
        }
        String i = String.valueOf(layerManager.getChoice());

        chooseLayer(i, model);


        return "editor";
    }


    @GetMapping("/changeColor")
    public String changeColor(@RequestParam(name = "id", required = false) String id,
                              @RequestParam(name = "color", required = false) String color,
                              Model model) {

        try {
            layerManager.getAbstractFactory().changeColor(Integer.parseInt(id), color);
            printFigures(model);
            layerManager.updateProxySVG();
        } catch (IllegalArgumentException e){
            String alert = "<script> alert('" + e.getMessage() + "') </script>";
            System.out.println(alert);
            model.addAttribute("changeError", alert);
        }
        return "editor";

    }

    @PostMapping("/delete")
    public String delete(@RequestParam(name = "id", required = false, defaultValue = "10") String id,
                         Model model) {
        System.out.println("delete main");

        try {
            layerManager.getAbstractFactory().deleteShape(Integer.parseInt(id));
            layerManager.updateProxySVG();
            printFigures(model);

        } catch (IllegalArgumentException e){
            String alert = "<script> alert('" + e.getMessage() + "') </script>";
            System.out.println(alert);
            model.addAttribute("deleteError", alert);
        }
        String i = String.valueOf(layerManager.getChoice());

        chooseLayer(i, model);

        return "editor";

    }

    @GetMapping("/chooseLayer")
    public String chooseLayer(@RequestParam(name = "id", required = true, defaultValue = "0") String id, Model model) {
        if(id.equals("all")) {
            figures=" ";
            String idList = "";
            StringBuffer buf = new StringBuffer();
            for(AbstractFactory a : layerManager.getAbstractFactoryMap()){
                if(a.getShapes().size() > 0) {
                    for (int i = 0; i < a.getShapes().size(); i++) {
                        figures = figures + a.getShapes().get(i).generateHtml();
                        buf.append("<li>" + a.getShapes().get(i).getId() + "</li>");
                        }
                }
                idList = buf.toString();
                model.addAttribute("htmlCode", figures);
                model.addAttribute("getAllId", idList);
            }
        } else {
            try {
                layerManager.chooseLayer((Integer.parseInt(id)));
            } catch (IllegalArgumentException e){
                String alert = "<script> alert('" + e.getMessage() + "') </script>";
                System.out.println(alert);
                model.addAttribute("idError", alert);
            }
            printFigures(model);
        }
        return "editor";
    }

    @GetMapping("/move")
    public String move(@RequestParam(name = "id", required = false, defaultValue = "10") int id,
                       @RequestParam(name = "direction", required = false, defaultValue = " ") String direction,
                       Model model) {
        try {
           // System.out.println("Controller: " + direction);
            layerManager.getAbstractFactory().moveShape(id, direction);
            layerManager.updateProxySVG();
            printFigures(model);
        }
        catch (IllegalArgumentException e){
            String alert = "<script> alert('" + e.getMessage() + "') </script>";
            System.out.println(alert);
            model.addAttribute("moveError", alert);
        }
            return "editor";


    }

    @GetMapping("/resize")
    public String resize(@RequestParam(name = "id", required = false) int id,
                         @RequestParam(name = "direction") String direction,
                         Model model) {
        layerManager.getAbstractFactory().scaleShape(id, direction);
        layerManager.updateProxySVG();
        printFigures(model);
        return "editor";
    }

    private void printFigures(Model model){

        figures=" ";
        StringBuffer buf = new StringBuffer();
        String idList = "";
       // System.out.println("size shape: "+ layerManager.getAbstractFactory().getShapes().size());
        if(layerManager.getAbstractFactory().getShapes().size() > 0 && layerManager.getObserver().listId.size() > 0) {
            for (int i = 0; i < layerManager.getAbstractFactory().getShapes().size(); i++) {
                figures = figures + layerManager.getAbstractFactory().getShapes().get(i).generateHtml();
                buf.append("<li>" + layerManager.getObserver().listId.get(i) + "</li>");
            }
        }
        idList = buf.toString();

        model.addAttribute("htmlCode", figures);
        model.addAttribute("getAllId", idList);
    }


}