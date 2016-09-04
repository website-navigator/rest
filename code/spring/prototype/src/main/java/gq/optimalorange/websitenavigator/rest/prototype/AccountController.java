package gq.optimalorange.websitenavigator.rest.prototype;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.annotation.Nonnull;

import gq.optimalorange.account.Identifier;
import gq.optimalorange.account.SubjectService;
import gq.optimalorange.account.SubjectService.ExistFailure;

@RestController
public class AccountController {

  private final SubjectService subjectService =
      Application.getInstance().serviceComponent.getSubjectService();


  @RequestMapping(value = "/accounts/exist", params = "id")
  public DeferredResult<ResultWrapper<Void, ExistFailure>> idExists(
      @RequestParam(name = "id") String id) {
    Identifier identifier = Identifier.id(id);
    return exists(identifier);
  }

  @RequestMapping(value = "/accounts/exist", params = "username")
  public DeferredResult<ResultWrapper<Void, ExistFailure>> usernameExists(
      @RequestParam(name = "username") String username) {
    Identifier identifier = Identifier.username(username);
    return exists(identifier);
  }

  private DeferredResult<ResultWrapper<Void, ExistFailure>> exists(@Nonnull Identifier identifier) {
    DeferredResult<ResultWrapper<Void, ExistFailure>> result = new DeferredResult<>();
    subjectService.exist(identifier)
        .subscribe(r -> result.setResult(ResultWrapper.of(r)), result::setErrorResult);
    return result;
  }

}
