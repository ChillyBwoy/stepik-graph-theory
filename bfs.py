def search(v):
    li = []
    red[v] = True
    # print(v)
    # ну тут думаю понятно, крашу вершину, которую обрабатываю

    for i in dic[v]:  # список вида { вершина: [смежные ей] }
        if not red[i]:
            queue.append(i)  # в очередь добавляю смежные вершины, которые ещё не пройдены
            li.append(i)  # и соответственно их же в "список уровня"

    if len(li) != 0:
        out.append(li)  # тут дабы индекс совпал с номером уровня

    # print("q`",queue)
    if v in queue:
        queue.remove(v)  # убираю из очереди обработанную только что вершину

    for el in queue:
        if not red[el]:
            red[el] = True
            search(el)  # и пошёл очередь перебирать
