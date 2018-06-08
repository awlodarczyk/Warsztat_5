package pl.coderslab.model;

public class JsonResponse {

    private String response;
    private Object object;
    public JsonResponse(String response,Object obj) {
        this.response = response;
        this.object = obj;
    }
    public JsonResponse(String response) {
        this.response = response;
        this.object = null;
    }
    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
