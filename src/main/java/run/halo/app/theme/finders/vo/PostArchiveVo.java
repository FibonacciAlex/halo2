package run.halo.app.theme.finders.vo;

import lombok.Builder;
import lombok.Value;

import java.util.List;

/**
 * Post archives by year and month.
 *
 * @author guqing
 * @since 2.0.0
 */
@Value
@Builder
public class PostArchiveVo {

    String year;

    List<PostArchiveYearMonthVo> months;
}
