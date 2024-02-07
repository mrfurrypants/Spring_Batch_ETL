CREATE UNIQUE INDEX records_uniqueness_index
ON public.covid_data
USING btree (
COALESCE(country, ''::character varying),
COALESCE(indicator, ''::character varying),
COALESCE(year_week, ''::character varying));

CREATE OR REPLACE PROCEDURE insert_record(country varchar(255), country_code varchar(255), continent varchar(255), population int4, indicator varchar(255), weekly_count int4, year_week varchar(255), rate_14_day numeric(20, 10), cumulative_count int4, source varchar(255), note varchar(255))
LANGUAGE plpgsql
AS $$
BEGIN
  BEGIN
    INSERT INTO covid_data (country, country_code, continent, population, indicator, weekly_count, year_week, rate_14_day, cumulative_count, source, note)
    VALUES (country, country_code, continent, population, indicator, weekly_count, year_week, rate_14_day, cumulative_count, source, note);
  EXCEPTION WHEN unique_violation THEN
  END;
END;
$$;