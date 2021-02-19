package longpolling.controller;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Collection;

@RestController
public class LongPollingController {
    private Multimap<String, DeferredResult<String>> watchRequests = Multimaps.synchronizedSetMultimap(HashMultimap.create());

    @RequestMapping(value = "/listen/{key}", method = RequestMethod.GET)
    public DeferredResult<String> listen(@PathVariable("key") String key) {
        DeferredResult<String> deferredResult = new DeferredResult<>();
        deferredResult.onCompletion(new Runnable() {
            @Override
            public void run() {
                System.out.println("remove key:" + key);
                watchRequests.remove(key, deferredResult);
            }
        });
        watchRequests.put(key, deferredResult);
        return deferredResult;
    }


    @RequestMapping(value = "/publish/{key}", method = RequestMethod.GET)
    public Object publishConfig(@PathVariable("key") String key) {
        if (watchRequests.containsKey(key)) {
            Collection<DeferredResult<String>> deferredResults = watchRequests.get(key);
            for (DeferredResult<String> deferredResult : deferredResults) {
                deferredResult.setResult(key + " changed");
            }
        }
        return "ok";
    }
}
