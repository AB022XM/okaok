

// public class  ResponseUtil
// {

//     @Autowired
//    RestTemplate restTemplate;

//     public String  getResponse() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<Tra> entity = new HttpEntity<Product>(product,headers);

//        return restTemplate.exchange(
//           "http://localhost:8080/products", HttpMethod.POST, entity, String.class).getBody();
//     }

// }
